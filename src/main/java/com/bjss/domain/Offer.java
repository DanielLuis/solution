package com.bjss.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Offer {
	private final Double discount;
	private final LocalDateTime start;
	private final LocalDateTime end;
}
