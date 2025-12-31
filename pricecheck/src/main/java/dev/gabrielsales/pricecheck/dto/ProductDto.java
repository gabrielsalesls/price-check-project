package dev.gabrielsales.pricecheck.dto;

import java.math.BigDecimal;

public record ProductDto(String name, String slug, BigDecimal price, Boolean available, ProviderDto provider) {
}
