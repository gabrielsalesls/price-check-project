package dev.gabrielsales.pricecheck.client;

import dev.gabrielsales.pricecheck.dto.ProductResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class ProviderBApiClient implements ProductProviderClient {

    private final RestClient restClient;

    public ProviderBApiClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return this.restClient.get()
                .uri("/")
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProductResponse>>() {});
    }

    @Override
    public ProductResponse getProductBySlug(String slug) {
        return this.restClient.get()
                .uri("/{slug}", slug)
                .retrieve()
                .body(new ParameterizedTypeReference<ProductResponse>() {});
    }
}
