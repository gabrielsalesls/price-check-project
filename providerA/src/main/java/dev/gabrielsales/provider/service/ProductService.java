package dev.gabrielsales.provider.service;

import dev.gabrielsales.provider.dto.ProductDto;
import dev.gabrielsales.provider.exception.ProductNotFoundException;
import dev.gabrielsales.provider.model.ProductInfo;
import dev.gabrielsales.provider.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDto> getAllProducts() {

        var productsData = repository.findAll();

        return productsData.stream().map(ProductInfo::toDto).toList();

    }

    public ProductDto getByProductSlug(String slug) {

        return repository.findBySlug(slug)
                .map(ProductInfo::toDto)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado: " + slug));
    }

}
