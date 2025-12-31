package dev.gabrielsales.provider.controller;

import dev.gabrielsales.provider.dto.ProductDto;
import dev.gabrielsales.provider.dto.ProductPurchaseDataDto;
import dev.gabrielsales.provider.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ProductDto> getProductBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(productService.getByProductSlug(slug));
    }

    @GetMapping("/{slug}/{id}")
    public ResponseEntity<ProductPurchaseDataDto> getProductPurchase(@PathVariable String slug, @PathVariable Long id) {
        return ResponseEntity.ok(productService.getPurchaseInfo(slug, id));
    }

}
