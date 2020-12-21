package com.bjss.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.bjss.domain.Goods;
import com.bjss.factory.OffersFactory;

@DisplayName("Test special offers")
class TestSpecialOffers {

	private final OffersFactory offersFactory = OffersFactory.getInstance();

	@DisplayName("Test soup special offer")
	@ParameterizedTest(name = "#{index} - Goods: {0} | Expected: {1}")
	@MethodSource("testSoupSpecialOfferProvider")
	void testSoupSpecialOffer(List<Goods> goods, double expected) {
		// When
		final double actual = SpecialOffers.applyAndGetSpecialOffers(goods);

		// Then
		assertEquals(expected, actual);
	}

	private static Stream<Arguments> testSoupSpecialOfferProvider() {
		final List<Goods> APPLES_10_OFF = Arrays.asList(Goods.APPLES, Goods.MILKS);

		final List<Goods> LOAF_OF_BREAD_50_OFF = Arrays.asList(Goods.BREAD, Goods.SOUP, Goods.SOUP);

		final List<Goods> NO_DISCOUNT = Arrays.asList(Goods.MILKS, Goods.SOUP);

		return Stream.of(Arguments.of(APPLES_10_OFF, 0.1),
				Arguments.of(LOAF_OF_BREAD_50_OFF, 0.4),
				Arguments.of(NO_DISCOUNT, 0.0),
				Arguments.of(null, 0.0)
		);
	}

}