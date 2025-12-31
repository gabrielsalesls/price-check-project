package dev.gabrielsales.pricecheck.dto;

import java.net.URI;

public record ProviderDto(String name, Long externalProductId, URI purchaseUrl) {
}
