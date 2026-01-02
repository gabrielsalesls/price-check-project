package dev.gabrielsales.pricecheck.dto;

import java.math.BigDecimal;
import java.net.URI;

public record ProviderDto(String name,  BigDecimal price, Long externalProductId, URI purchaseUrl) {
}
