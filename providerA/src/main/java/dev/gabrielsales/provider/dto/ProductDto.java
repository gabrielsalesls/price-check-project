package dev.gabrielsales.provider.dto;

import java.math.BigDecimal;

public record ProductDto(Long id, String name, String slug, BigDecimal value, boolean available) {
}
