package dev.gabrielsales.pricecheck.client;

import dev.gabrielsales.pricecheck.dto.ProductResponse;

import java.util.List;

public interface ProductProviderClient {

    List<ProductResponse> getAllProducts();

    ProductResponse getProductBySlug(String slug);

}