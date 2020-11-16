package com.downjones.stock.challenge.repository;

import java.util.List;

import com.downjones.stock.challenge.domain.WeeklyStock;

import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyStockCustomRepository {
	List<WeeklyStock> findWeeklyStocksByStockTicker(String stockTicker);
}
