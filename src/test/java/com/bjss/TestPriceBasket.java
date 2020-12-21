package com.bjss;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.bjss.domain.Goods;

@DisplayName("Test price basket")
class TestPriceBasket {

	@DisplayName("Test pricing basket goods")
	@ParameterizedTest(name = "#{index} - Goods: {0}")
	@MethodSource("testPricingBasketGoodsProvider")
	void testPricingBasketGoods(List<String> goods) {
		PriceBasket.pricingBasketGoods(goods);
	}

	@DisplayName("Test get total price")
	@ParameterizedTest(name = "#{index} - Goods: {0} | Expected: {1}")
	@MethodSource("testGetTotalPriceProvider")
	void testGetTotalPrice(List<Goods> goods, double expected) {
		// When
		final double actual = PriceBasket.getTotalPrice(goods);

		// Then
		assertEquals(expected, actual);
	}

	private static Stream<Arguments> testGetTotalPriceProvider() {
		List<Goods> goods = Arrays.asList(Goods.APPLES, Goods.BREAD);
		List<Goods> allGoods = Arrays.asList(Goods.values());
		List<Goods> emptyList = new ArrayList<>();

		return Stream.of(Arguments.of(goods, 1.8),
				Arguments.of(null, 0.0),
				Arguments.of(emptyList, 0.0),
				Arguments.of(allGoods, 3.75));
	}

	private static Stream<Arguments> testPricingBasketGoodsProvider() {
		List<String> goods = Arrays.asList("APPLES", "BREAD");
		List<String> allGoods = Arrays.asList("APPLES", "BREAD", "SOUP", "MILK");
		List<String> emptyList = new ArrayList<>();
		List<String> discountZero = Collections.singletonList("BREAD");
		List<String> invalidGoods = Collections.singletonList("INVALID");

		return Stream.of(Arguments.of(goods),
				Arguments.of(emptyList),
				null,
				Arguments.of(discountZero),
				Arguments.of(invalidGoods),
				Arguments.of(allGoods));
	}
}