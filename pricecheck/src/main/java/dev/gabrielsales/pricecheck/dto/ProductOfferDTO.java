package dev.gabrielsales.pricecheck.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record ProductOfferDTO(
        ProviderDto provider,
        @JsonIgnore
        String name) {
}
