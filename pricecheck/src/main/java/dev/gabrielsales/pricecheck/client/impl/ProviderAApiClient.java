package dev.gabrielsales.pricecheck.client.impl;

import dev.gabrielsales.pricecheck.client.ProductProviderClient;
import dev.gabrielsales.pricecheck.client.dto.ProviderProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

public class ProviderAApiClient implements ProductProviderClient {

    @Value("${provider-a.active:false}")
    private boolean isProviderActive;

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
    public Optional<ProviderProductResponse> getProductBySlug(String slug) {
        try {
            ProviderProductResponse response = this.restClient.get()
                    .uri("/{slug}", slug)
                    .retrieve()
                    .body(ProviderProductResponse.class);

            return Optional.ofNullable(response);
        } catch (HttpClientErrorException.NotFound ex) {
            return Optional.empty();
        }
    }

    @Override
    public boolean isProviderActive() {
        return isProviderActive;
    }
}
