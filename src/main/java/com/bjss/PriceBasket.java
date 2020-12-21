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

		final List<Goods> listGoods =
				args.stream()
						.map(Goods::findByName)
						.collect(Collectors.toList());

		//TODO - Should be removed before the release
		listGoods.forEach(goods -> System.out.println(goods != null ? goods.getName() : "there's a invalid item"));

		double subtotal = getTotalPrice(listGoods);

		Util.write("Subtotal: ", subtotal, "");

		double discount = SpecialOffers.appleSpecialOffers(listGoods);

		double total = subtotal - discount;

		if (discount == 0.0) {
			Util.write("(no offers available)");
		}
		Util.write("Total: ", total);
	}

	/**
	 * Sum all prices
	 */
	private static double getTotalPrice(List<Goods> listGoods) {
		return listGoods.stream()
				.filter(Objects::nonNull)
				.mapToDouble(Goods::getPrice)
				.sum();
	}
}
