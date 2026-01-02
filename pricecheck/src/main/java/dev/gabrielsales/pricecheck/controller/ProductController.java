package dev.gabrielsales.pricecheck.controller;

import dev.gabrielsales.pricecheck.dto.ProductDataDto;
import dev.gabrielsales.pricecheck.dto.ProductPriceComparasionDto;
import dev.gabrielsales.pricecheck.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<List<ProductDataDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ProductPriceComparasionDto> getBestPriceByProductSlug(@PathVariable String slug){
        return ResponseEntity.ok(productService.getBestPriceBySlug(slug));
    }
}
