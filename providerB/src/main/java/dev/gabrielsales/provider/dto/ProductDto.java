package dev.gabrielsales.provider.dto;

import java.math.BigDecimal;
import java.net.URI;

public record ProductDto(Long id, String providerName, String productName, String slug, BigDecimal value, boolean available, URI purchaseUrl) {
}
