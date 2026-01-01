package dev.gabrielsales.provider.service;

import dev.gabrielsales.provider.dto.ProductDto;
import dev.gabrielsales.provider.dto.ProductPurchaseDataDto;
import dev.gabrielsales.provider.exception.InvalidSlugException;
import dev.gabrielsales.provider.exception.ProductNotFoundException;
import dev.gabrielsales.provider.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDto> getAllProducts() {

        var productsData = repository.findAll();

        var productResponse = productsData.stream()
                .map(product -> {

                    var purchaseUrl = URI.create(String.format("http://localhost:8082/api/provider-b/products/%s/%s", product.getSlug(), product.getId()));

                    return new ProductDto(product.getId(), "provider_b", product.getName(), product.getSlug(), product.getPrice(), product.getAvailable(), purchaseUrl);
                });

        return productResponse.toList();

    }

    public ProductDto getByProductSlug(String slug) {

        var productData = repository.findBySlug(slug);

        if (productData.isEmpty()) {
            throw new ProductNotFoundException("Produto não encontrado: " + slug);
        }

        var product = productData.get();
        var purchaseUrl = URI.create(String.format("http://localhost:8081/api/products/%s/%s", product.getSlug(), product.getId()));

        return new ProductDto(product.getId(), "provider_b", product.getName(), product.getSlug(), product.getPrice(), product.getAvailable(), purchaseUrl);

    }

    public ProductPurchaseDataDto getPurchaseInfo(String slug, Long id) {
        var productData = repository.findById(id);

        if (productData.isEmpty()) {
            throw new ProductNotFoundException("Product not found: " + slug);
        }

        var productSlug = productData.get().getSlug();
        if (!Objects.equals(slug, productSlug)) {
            throw new InvalidSlugException("Invalid slug: " + slug);
        }
        return new ProductPurchaseDataDto(productData.get().getId(), productData.get().getName(), productData.get().getPrice());
    }
}
