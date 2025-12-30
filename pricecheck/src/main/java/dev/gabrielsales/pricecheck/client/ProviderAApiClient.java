package dev.gabrielsales.pricecheck.client;

import dev.gabrielsales.pricecheck.dto.ProductResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class ProviderAApiClient {

    private final RestClient restClient;

    public ProviderAApiClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<ProductResponse> getAllProducts() {

        return this.restClient.get()
                .uri("/")
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProductResponse>>() {});
    }
}
