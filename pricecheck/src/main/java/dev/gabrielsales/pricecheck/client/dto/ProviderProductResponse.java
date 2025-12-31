package dev.gabrielsales.pricecheck.client.dto;

import java.math.BigDecimal;

public record ProviderProductResponse(
        Long id,
        String name,
        String slug,
        BigDecimal value,
        boolean available
) {}