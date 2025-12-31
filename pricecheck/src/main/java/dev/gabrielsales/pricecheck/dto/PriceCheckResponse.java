package dev.gabrielsales.pricecheck.dto;

import java.util.List;

public record PriceCheckResponse(String product, String normalizedProduct, BestPriceDto bestPriceDto, List<ProductResponse> productResponseList) {
}
