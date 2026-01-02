package dev.gabrielsales.pricecheck.service;

import dev.gabrielsales.pricecheck.client.ProductProviderClient;
import dev.gabrielsales.pricecheck.client.dto.ProviderProductResponse;
import dev.gabrielsales.pricecheck.dto.ProductDataDto;
import dev.gabrielsales.pricecheck.dto.ProductOfferDTO;
import dev.gabrielsales.pricecheck.dto.ProductPriceComparasionDto;
import dev.gabrielsales.pricecheck.dto.ProductSummaryDto;
import dev.gabrielsales.pricecheck.exception.ProductNotFoundException;
import dev.gabrielsales.pricecheck.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final List<ProductProviderClient> clients;

    public ProductService(List<ProductProviderClient> clients) {
        this.clients = clients;
    }

    public List<ProductDataDto> getAllProducts() {

        var activeProviders = getActiveProviders();

        var providersProductsList = fetchAllProductsFromProviders(activeProviders);

        return providersProductsList
                .stream().map(ProductMapper::getProductDataDto)
                .toList();
    }

    public ProductPriceComparasionDto getBestPriceBySlug(String slug) {
        var activeProviders = getActiveProviders();

        List<ProviderProductResponse> allOffers = getProviderProductResponses(slug, activeProviders);

        if (allOffers.isEmpty()) {
            throw new ProductNotFoundException("Nenhuma oferta encontrada para o produto: " + slug);
        }

        ProviderProductResponse bestOfferResponse = allOffers.stream()
                .min(Comparator.comparing(ProviderProductResponse::value))
                .orElseThrow();

        ProductOfferDTO bestOffer = ProductMapper.convertToProductOfferDto(bestOfferResponse);

        List<ProductOfferDTO> alternativeOffers = allOffers.stream()
                .filter(offer -> offer != bestOfferResponse)
                .map(ProductMapper::convertToProductOfferDto)
                .toList();

        ProductSummaryDto productSummary = new ProductSummaryDto(bestOffer.name());

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
