package com.bjss;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.bjss.domain.Goods;
import com.bjss.service.SpecialOffers;
import com.bjss.util.Util;

public class PriceBasket {

	public static void main(String[] args) {
		pricingBasketGoods(Arrays.asList(args));
	}

	/**
	 * Calculate discount and pricing of basket goods
	 */
	public static void pricingBasketGoods(List<String> args) {

		if (args == null || args.isEmpty()) {
			return;
		}

		final List<Goods> listGoods =
				args.stream()
						.map(Goods::findByName)
						.collect(Collectors.toList());

		double subtotal = getTotalPrice(listGoods);

		Util.write("Subtotal: ", subtotal, "");

		double discount = SpecialOffers.applyAndGetSpecialOffers(listGoods);

		double total = subtotal - discount;

		if (discount == 0.0) {
			Util.write("(no offers available)");
		}
		Util.write("Total: ", total);
	}

	/**
	 * Sum all prices
	 */
	static double getTotalPrice(List<Goods> listGoods) {
		if (listGoods != null && !listGoods.isEmpty()) {
			return listGoods.stream()
					.filter(Objects::nonNull)
					.mapToDouble(Goods::getPrice)
					.sum();

		}
		return 0.0;
	}
}
