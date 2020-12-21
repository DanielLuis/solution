package com.bjss.service;

import java.util.List;
import java.util.Optional;

import com.bjss.domain.Goods;
import com.bjss.domain.Offer;
import com.bjss.factory.OffersFactory;
import com.bjss.util.Util;

import lombok.extern.slf4j.Slf4j;

/**
 * Class to apply any discount on goods
 */
@Slf4j
public class SpecialOffers {

	public static final double ZERO = 0.0;

	private static final OffersFactory offersFactory = OffersFactory.getInstance();

	//Apply and sum all discounts rules
	public static double applyAndGetSpecialOffers(List<Goods> goods) {
		if (goods != null && !goods.isEmpty()) {
			final double appleSpecialOffer = appleSpecialOffer(goods);
			final double soupSpecialOffer = soupSpecialOffer(goods);

			return appleSpecialOffer + soupSpecialOffer;
		}
		return 0.0;
	}

	/**
	 * Apply the APPLES discount rule
	 * Apples 10% off this week
	 *
	 * @return discount
	 */
	static double appleSpecialOffer(List<Goods> goods) {

		try {
			final Optional<Offer> appleOffer = offersFactory.getValidOfferByGoods(Goods.APPLES);

			return appleOffer.map(offer ->
					goods.stream()
							.filter(g -> g != null && Goods.APPLES.getName().equalsIgnoreCase(g.getName()))
							.map(item -> item.getPrice() * offer.getDiscount())
							.reduce(Double::sum)
							.map(value -> {
								Util.writeDiscounts("Apples 10% off: ", value);
								return value;
							})
							.orElse(ZERO)
			).orElse(ZERO);
		}
		catch (Exception e) {
			log.error("Error when executing method 'appleSpecialOffer' for= {}", goods, e);
			return 0.0;
		}
	}

	/**
	 * Apply the SOUP discount rule
	 * Calculate total discount on Bread (Buy 2 tin and get a loaf of bread for a half price)
	 *
	 * @return discount
	 */
	static double soupSpecialOffer(List<Goods> goods) {

		try {
			final Optional<Offer> breadOffer = offersFactory.getValidOfferByGoods(Goods.BREAD);

			return breadOffer.map(offer -> {
				//Check if the SOUP offer can be applied
				final boolean hasMoreThanOneSoup = goods.stream()
						.filter(g -> g != null && Goods.SOUP.getName().equalsIgnoreCase(g.getName()))
						.count() > 1;

				//If there more than one SOUP in the Basket, I check and apply the discount for one bread
				if (hasMoreThanOneSoup) {
					return goods.stream()
							.filter(g -> Goods.BREAD.getName().equals(g.getName()))
							.map(bread -> {
								final double discount = bread.getPrice() * offer.getDiscount();
								Util.writeDiscounts("One loaf of bread for half price: ", discount);
								return discount;
							})
							.findFirst()
							.orElse(ZERO);
				}
				return ZERO;
			}).orElse(ZERO);
		}
		catch (Exception e) {
			log.error("Error when executing method 'soupSpecialOffer' for= {}", goods, e);
			return 0.0;
		}

	}
}
