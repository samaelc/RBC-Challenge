package com.downjones.stock.challenge.service;

import java.util.List;

import com.downjones.stock.challenge.domain.WeeklyStock;

import org.springframework.web.multipart.MultipartFile;

public interface WeeklyStockService {
	void save(MultipartFile file);

	List<WeeklyStock> findWeeklyStockByTicker(String stockTicker);

	void saveWeeklyStock(WeeklyStock weeklyStock);
}
