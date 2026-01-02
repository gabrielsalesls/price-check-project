package dev.gabrielsales.pricecheck.mapper;

import dev.gabrielsales.pricecheck.client.dto.ProviderProductResponse;
import dev.gabrielsales.pricecheck.dto.ProductDataDto;
import dev.gabrielsales.pricecheck.dto.ProductOfferDTO;
import dev.gabrielsales.pricecheck.dto.ProviderDto;

public final class ProductMapper {

    private ProductMapper() {}

    public static ProductDataDto getProductDataDto(ProviderProductResponse product) {
        var providerDto = new ProviderDto(product.providerName(), product.value(), product.id(), product.purchaseUrl());
        return new ProductDataDto(product.productName(), product.slug(), providerDto);
    }

    public static ProductOfferDTO convertToProductOfferDto(ProviderProductResponse product) {
        var providerDto = new ProviderDto(product.providerName(), product.value(), product.id(), product.purchaseUrl());
        return new ProductOfferDTO(providerDto, product.productName());
    }
}
