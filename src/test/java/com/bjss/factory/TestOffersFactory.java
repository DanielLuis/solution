package com.bjss.factory;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.bjss.domain.Goods;
import com.bjss.domain.Offer;

@DisplayName("Test offers factory")
class TestOffersFactory {

	private final OffersFactory offersFactory = OffersFactory.getInstance();

	@DisplayName("Test get valid offer by goods")
	@ParameterizedTest(name = "#{index} - Goods: {0} | Expected: {1}")
	@MethodSource("testGetValidOfferByGoodsProvider")
	void testGetValidOfferByGoods(Goods goods, Optional<Offer> expected) {

		// When
		final Optional<Offer> actual = this.offersFactory.getValidOfferByGoods(goods);

		// Then
		if (expected.isPresent() && actual.isPresent()) {
			Assertions.assertEquals(expected.get().getDiscount(), actual.get().getDiscount());
		} else {
			Assertions.assertEquals(expected, actual);
		}
	}

	private static Stream<Arguments> testGetValidOfferByGoodsProvider() {
		final Optional<Offer> applesOffer = Optional.of(Offer.builder().discount(0.1).build());
		final Optional<Offer> breadOffer = Optional.of(Offer.builder().discount(0.5).build());

		return Stream.of(Arguments.of(Goods.BREAD, breadOffer),
				Arguments.of(Goods.APPLES, applesOffer),
				Arguments.of(Goods.SOUP, Optional.empty()),
				Arguments.of(Goods.MILKS, Optional.empty()));
	}
}