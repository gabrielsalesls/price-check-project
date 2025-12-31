package dev.gabrielsales.provider.controller;

import dev.gabrielsales.provider.dto.ProductDto;
import dev.gabrielsales.provider.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
public class ProviderController {

    private final ProductService productService;

    public ProviderController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ProductDto> getByProductSlug(@PathVariable String slug) {
        return ResponseEntity.ok(productService.getByProductSlug(slug));
    }

}
