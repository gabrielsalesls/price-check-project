package dev.gabrielsales.provider.service;

import dev.gabrielsales.provider.dto.ProductDto;
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

    public ProductDto getProductByName(String name) {

        var productData = repository.findByProductName(name);

        return productData.getFirst().toDto();
    }

}
