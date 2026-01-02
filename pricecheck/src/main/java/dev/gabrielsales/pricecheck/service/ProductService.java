package dev.gabrielsales.pricecheck.service;

import dev.gabrielsales.pricecheck.client.ProductProviderClient;
import dev.gabrielsales.pricecheck.client.dto.ProviderProductResponse;
import dev.gabrielsales.pricecheck.dto.ProductDataDto;
import dev.gabrielsales.pricecheck.dto.ProductOfferDTO;
import dev.gabrielsales.pricecheck.dto.ProductPriceComparasionDto;
import dev.gabrielsales.pricecheck.dto.ProductSummaryDto;
import dev.gabrielsales.pricecheck.exception.ProductNotFoundException;
import dev.gabrielsales.pricecheck.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    private final List<ProductProviderClient> clients;

    public ProductService(List<ProductProviderClient> clients) {
        this.clients = clients;
    }

    public List<ProductDataDto> getAllProducts() {

        var activeProviders = getActiveProviders();

        var providersProductsList = fetchAllProductsFromProviders(activeProviders);

        var groupedProductsBySlug = providersProductsList.stream()
                .filter(ProviderProductResponse::available)
                .collect(Collectors.groupingBy(ProviderProductResponse::slug));

        var bestOfferPerProduct = groupedProductsBySlug.values().stream()
                .map(producto -> {
                    var bestOfferByProduct = producto.stream()
                            .min(Comparator.comparing(ProviderProductResponse::value))
                            .orElseThrow();
                    return ProductMapper.getProductDataDto(bestOfferByProduct);
                });

        return bestOfferPerProduct.toList();
    }

    public ProductPriceComparasionDto getBestPriceBySlug(String slug) {
        var activeProviders = getActiveProviders();

        List<ProviderProductResponse> allOffers = getProviderProductResponses(slug, activeProviders);

        if (allOffers.isEmpty()) {
            throw new ProductNotFoundException("Nenhuma oferta encontrada para o produto: " + slug);
        }

        ProviderProductResponse bestOfferResponse = allOffers.stream()
                .filter(ProviderProductResponse::available)
                .min(Comparator.comparing(ProviderProductResponse::value))
                .orElseThrow();

        ProductOfferDTO bestOffer = ProductMapper.convertToProductOfferDto(bestOfferResponse);

        List<ProductOfferDTO> alternativeOffers = allOffers.stream()
                .filter(offer -> offer != bestOfferResponse)
                .map(ProductMapper::convertToProductOfferDto)
                .toList();

        ProductSummaryDto productSummary = new ProductSummaryDto(bestOffer.name(), slug);

        return new ProductPriceComparasionDto(productSummary, bestOffer, alternativeOffers);

    }

    private static List<ProviderProductResponse> getProviderProductResponses(String slug, List<ProductProviderClient> activeProviders) {
        return activeProviders.stream()
                .flatMap(client -> {
                    try {
                        return client.getProductBySlug(slug).stream();
                    } catch (Exception e) {
                        return Stream.empty();
                    }
                })
                .toList();
    }

    private List<ProductProviderClient> getActiveProviders() {
        return clients.stream().filter(ProductProviderClient::isProviderActive).toList();
    }

    private List<ProviderProductResponse> fetchAllProductsFromProviders(List<ProductProviderClient> activeProviders) {
        return activeProviders.stream()
                .flatMap(client -> {
                    try {
                        return client.getAllProducts().stream();
                    } catch (Exception e) {
                        //Error
                        return Stream.empty();
                    }
                })
                .toList();
    }
}
