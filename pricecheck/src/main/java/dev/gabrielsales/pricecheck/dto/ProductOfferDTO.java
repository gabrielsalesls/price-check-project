package dev.gabrielsales.pricecheck.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public record ProductOfferDTO(
        BigDecimal price,
        boolean available,
        ProviderDto provider,
        @JsonIgnore
        String name) {
}
