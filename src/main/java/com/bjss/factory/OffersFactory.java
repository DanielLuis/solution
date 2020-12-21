package com.bjss.factory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.bjss.domain.Goods;
import com.bjss.domain.Offer;

import lombok.Getter;

@Getter
public class OffersFactory {

	private static final Offer OFFER_10_PERCENT_WEEKLY =
			Offer.builder().discount(0.1).start(LocalDateTime.now()).end(LocalDateTime.now().plusDays(7)).build();

	private static final Offer OFFER_50_PERCENT =
			Offer.builder().discount(0.5).start(LocalDateTime.now()).end(LocalDateTime.now().plusDays(7)).build();

	private final Map<Goods, Offer> offerMap = new HashMap<>();

	private static OffersFactory offersFactory;

	public static OffersFactory getInstance() {
		if (offersFactory == null) {
			offersFactory = new OffersFactory();
		}

		return offersFactory;
	}

	private OffersFactory() {
		//subscribe a product to an specific offer
		this.offerMap.put(Goods.APPLES, OFFER_10_PERCENT_WEEKLY);
		this.offerMap.put(Goods.BREAD, OFFER_50_PERCENT);
	}

	public Optional<Offer> getValidOfferByGoods(Goods goods) {
		final LocalDateTime CURRENT_DATE = LocalDateTime.now();

		return this.getOfferMap().entrySet()
				.stream()
				.filter(productOfferEntry -> productOfferEntry.getKey().equals(goods))
				.map(Map.Entry::getValue)
				.filter(offer -> CURRENT_DATE.isAfter(offer.getStart()) && CURRENT_DATE.isBefore(offer.getEnd()))
				.findFirst();
	}
}
