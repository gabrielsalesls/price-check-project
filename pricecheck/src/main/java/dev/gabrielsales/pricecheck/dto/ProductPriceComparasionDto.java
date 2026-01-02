package dev.gabrielsales.pricecheck.dto;

import java.util.List;

public record ProductPriceComparasionDto(ProductSummaryDto product, ProductOfferDTO bestOffer, List<ProductOfferDTO> otherOffers) {
}
