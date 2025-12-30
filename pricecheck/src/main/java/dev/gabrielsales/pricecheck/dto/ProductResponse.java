package dev.gabrielsales.pricecheck.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String slug,
        BigDecimal value,
        boolean available
) {}