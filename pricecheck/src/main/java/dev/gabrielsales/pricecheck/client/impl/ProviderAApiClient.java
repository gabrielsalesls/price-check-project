package dev.gabrielsales.pricecheck.client.impl;

import dev.gabrielsales.pricecheck.client.ProductProviderClient;
import dev.gabrielsales.pricecheck.client.dto.ProviderProductResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class ProviderAApiClient implements ProductProviderClient {

    private final RestClient restClient;

    public ProviderAApiClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<ProviderProductResponse> getAllProducts() {

        return this.restClient.get()
                .uri("/")
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProviderProductResponse>>() {});
    }

    @Override
    public ProviderProductResponse getProductBySlug(String slug) {
        return this.restClient.get()
                .uri("/{slug}", slug)
                .retrieve()
                .body(new ParameterizedTypeReference<ProviderProductResponse>() {});
    }
}
