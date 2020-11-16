package com.downjones.stock.challenge.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyStockId implements Serializable {
	private int quarter;
	private String stockId;
	private Date date;
}
