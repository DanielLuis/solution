package com.bjss.factory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.bjss.domain.Goods;
import com.bjss.domain.Offer;

import lombok.Getter;

/**
 * I've created this class to be used as a factory of offers to simulate a table where you can create offers and also associate a product to
 * specifics offers
 */
@Getter
public class OffersFactory {

	/**
	 * Weekly offer, valid for 7 days starting at the currenta date
	 */
	private static final Offer OFFER_10_PERCENT_WEEKLY =
			Offer.builder().discount(0.1).start(LocalDateTime.now()).end(LocalDateTime.now().plusDays(7)).build();

	/**
	 * 50% offer
	 */
	private static final Offer OFFER_50_PERCENT =
			Offer.builder().discount(0.5).start(LocalDateTime.now()).end(LocalDateTime.MAX).build();

	private final Map<Goods, Offer> offerMap = new HashMap<>();

	private static OffersFactory offersFactory;

	/**
	 * Singleton implementation
	 * @return OffersFactory
	 */
	public static OffersFactory getInstance() {
		if (offersFactory == null) {
			offersFactory = new OffersFactory();
		}

		return offersFactory;
	}

	/**
	 * Private constructor to avoid mora than one instantiation
	 */
	private OffersFactory() {
		//subscribe a product to an specific offer
		this.offerMap.put(Goods.APPLES, OFFER_10_PERCENT_WEEKLY);
		this.offerMap.put(Goods.BREAD, OFFER_50_PERCENT);
	}

	public Optional<Offer> getValidOfferByGoods(Goods goods) {
		final LocalDateTime currentDate = LocalDateTime.now();

		return this.getOfferMap().entrySet()
				.stream()
				.filter(productOfferEntry -> productOfferEntry.getKey().equals(goods))
				.map(Map.Entry::getValue)
				.filter(offer -> currentDate.isAfter(offer.getStart()) && currentDate.isBefore(offer.getEnd()))
				.findFirst();
	}
}
