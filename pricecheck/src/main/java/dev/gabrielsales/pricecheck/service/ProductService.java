package dev.gabrielsales.pricecheck.service;

import dev.gabrielsales.pricecheck.client.ProductProviderClient;
import dev.gabrielsales.pricecheck.dto.ProductDto;
import dev.gabrielsales.pricecheck.dto.ProviderDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ProductService {

    private final List<ProductProviderClient> clients;

    public ProductService(List<ProductProviderClient> clients) {
        this.clients = clients;
    }

    public List<ProductDto> getAllProducts() {

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
            return new ProductDto(product.productName(), product.slug(), product.value(), product.available(), providerDto);
        }).toList();

    }

/*    public PriceCheckResponse getBestPriceBySlug(String slug) {
        var response = providerAApiClient.getProductBySlug(slug);

        var bestPrice =

        return new PriceCheckResponse(response.name(), response.slug(), )
    }*/

}
