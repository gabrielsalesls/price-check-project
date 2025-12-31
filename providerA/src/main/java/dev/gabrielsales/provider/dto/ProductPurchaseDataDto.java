package dev.gabrielsales.provider.dto;

import java.math.BigDecimal;

public record ProductPurchaseDataDto(Long id, String name, BigDecimal price) { }
