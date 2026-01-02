package dev.gabrielsales.pricecheck.client;

import dev.gabrielsales.pricecheck.client.dto.ProviderProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductProviderClient {

    List<ProviderProductResponse> getAllProducts();

    Optional<ProviderProductResponse> getProductBySlug(String slug);

    boolean isProviderActive();

}