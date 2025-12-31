package dev.gabrielsales.pricecheck.client.dto;

import java.math.BigDecimal;
import java.net.URI;

public record ProviderProductResponse(
        Long id,
        String providerName,
        String productName,
        String slug,
        BigDecimal value,
        boolean available,

        URI purchaseUrl
) {}