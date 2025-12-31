package dev.gabrielsales.pricecheck.controller;

import dev.gabrielsales.pricecheck.client.dto.ProviderProductResponse;
import dev.gabrielsales.pricecheck.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProviderProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

/*    @GetMapping("/{slug}")
    public ResponseEntity<PriceCheckResponse> getBestPriceByProductSlug(@PathVariable String slug){

    }*/
}
