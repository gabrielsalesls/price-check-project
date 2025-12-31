package dev.gabrielsales.pricecheck.service;

import dev.gabrielsales.pricecheck.client.ProductProviderClient;
import dev.gabrielsales.pricecheck.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final List<ProductProviderClient> clients;

    public ProductService(List<ProductProviderClient> clients) {
        this.clients = clients;
    }

    public List<ProductResponse> getAllProducts() {

        return clients.stream()
                .flatMap(client -> client.getAllProducts().stream())
                .toList();
    }

/*    public PriceCheckResponse getBestPriceBySlug(String slug) {
        var response = providerAApiClient.getProductBySlug(slug);

        var bestPrice =

        return new PriceCheckResponse(response.name(), response.slug(), )
    }*/

}
