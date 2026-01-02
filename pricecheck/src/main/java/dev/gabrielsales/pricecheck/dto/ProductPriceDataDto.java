package dev.gabrielsales.pricecheck.dto;

import java.util.List;

public record ProductPriceDataDto(ProductSummaryDto product, ProductOfferDTO bestOffer, List<ProductOfferDTO> otherOffers) {
}
