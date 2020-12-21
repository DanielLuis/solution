package com.bjss.domain;

import java.util.Arrays;

import lombok.Getter;

/*
Used as a table of goods
 */
@Getter
public enum Goods {
	APPLES("APPLES", 1.00),
	MILKS("MILK", 1.30),
	BREAD("BREAD", 0.80),
	SOUP("SOUP", 0.65);

	private final String name;
	private final Double price;

	Goods(String name, Double price) {
		this.name = name;
		this.price = price;
	}

	public static Goods findByName(String name) {
		return Arrays.stream(values())
				.filter(prod -> prod.getName().equalsIgnoreCase(name))
				.findAny()
				.orElse(null);
	}
}
