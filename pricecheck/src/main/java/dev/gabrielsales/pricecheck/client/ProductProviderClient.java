package dev.gabrielsales.pricecheck.client;

import dev.gabrielsales.pricecheck.client.dto.ProviderProductResponse;

import java.util.List;

public interface ProductProviderClient {

    List<ProviderProductResponse> getAllProducts();

    ProviderProductResponse getProductBySlug(String slug);

    boolean isProviderActive();

}