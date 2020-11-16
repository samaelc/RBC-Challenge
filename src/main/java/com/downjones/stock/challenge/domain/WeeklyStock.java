package com.downjones.stock.challenge.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyStock implements Serializable {

	@EmbeddedId
	private WeeklyStockId weeklyStockId;
	private double openPrice;
	private double highPrice;
	private double lowPrice;
	private double closePrice;
	private int volume;
	private double percentagePriceChange;
	private double percentageVolumeChange;
	private int previousWeekVolume;
	private double nextWeekOpenPrice;
	private double nextWeekClosePrice;
	private double nextWeekPercentagePriceChange;
	private int nextWeekDaysToNextDividend;
	private double percentageReturnNextDividend;
}
