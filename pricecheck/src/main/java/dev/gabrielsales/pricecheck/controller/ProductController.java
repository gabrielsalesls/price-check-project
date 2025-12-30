package dev.gabrielsales.pricecheck.controller;

import dev.gabrielsales.pricecheck.client.ProviderAApiClient;
import dev.gabrielsales.pricecheck.dto.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProviderAApiClient providerAApiClient;

    public ProductController(ProviderAApiClient providerAApiClient) {
        this.providerAApiClient = providerAApiClient;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(providerAApiClient.getAllProducts());
    }
}
