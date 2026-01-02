package dev.gabrielsales.pricecheck.service;

import dev.gabrielsales.pricecheck.client.ProductProviderClient;
import dev.gabrielsales.pricecheck.client.dto.ProviderProductResponse;
import dev.gabrielsales.pricecheck.dto.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ProductService {

    private final List<ProductProviderClient> clients;

    public ProductService(List<ProductProviderClient> clients) {
        this.clients = clients;
    }

    public List<ProductDataDto> getAllProducts() {

        var activeProviders = clients.stream().filter(ProductProviderClient::isProviderActive).toList();

        var providersProductsList = activeProviders.stream()
                .flatMap(client -> {
                    try {
                        return client.getAllProducts().stream();
                    } catch (Exception e) {
                        //Error
                        return Stream.empty();
                    }
                })
                .toList();

        return providersProductsList.stream().map(product -> {
            var providerDto = new ProviderDto(product.providerName(), product.id(), product.purchaseUrl());
            return new ProductDataDto(product.productName(), product.slug(), product.value(), product.available(), providerDto);
        }).toList();

    }

    public ProductPriceDataDto getBestPriceBySlug(String slug) {
        var activeProviders = clients.stream().filter(ProductProviderClient::isProviderActive).toList();

        var productInfoByProvider = activeProviders.stream()
                .flatMap(client -> client.getProductBySlug(slug).stream())
                .sorted(Comparator.comparing(ProviderProductResponse::value))
                .map(product -> {
                    var providerDto = new ProviderDto(product.providerName(), product.id(), product.purchaseUrl());
                    return new ProductOfferDTO(product.value(), product.available(), providerDto, product.productName());
                })
                .toList();

        Optional<ProductOfferDTO> produtoMaisBarato = productInfoByProvider.isEmpty()
                ? Optional.empty()
                : Optional.of(productInfoByProvider.get(0));

        List<ProductOfferDTO> listaSemMaisBarato = productInfoByProvider.stream()
                .skip(1)
                .toList();

        var productSummaryDto = new ProductSummaryDto(produtoMaisBarato.get().name());

        return new ProductPriceDataDto(productSummaryDto, produtoMaisBarato.get(), listaSemMaisBarato);

    }

}
