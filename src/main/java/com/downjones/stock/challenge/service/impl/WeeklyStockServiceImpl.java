package com.downjones.stock.challenge.service.impl;

import java.util.List;

import com.downjones.stock.challenge.domain.WeeklyStock;
import com.downjones.stock.challenge.repository.WeeklyStockCustomRepository;
import com.downjones.stock.challenge.repository.WeeklyStockRepository;
import com.downjones.stock.challenge.service.WeeklyStockService;
import com.downjones.stock.challenge.util.WeeklyStockFileHelper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class WeeklyStockServiceImpl implements WeeklyStockService {
	private final WeeklyStockRepository weeklyStockRepository;
	private final WeeklyStockCustomRepository weeklyStockCustomRepository;

	public void save(MultipartFile file) {
		try {
			List<WeeklyStock> weeklyStocks = WeeklyStockFileHelper.csvToWeeklyStock(file.getInputStream());
			weeklyStockRepository.saveAll(weeklyStocks);
		}
		catch (Exception e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	public List<WeeklyStock> findWeeklyStockByTicker(String stockTicker) {
		return weeklyStockCustomRepository.findWeeklyStocksByStockTicker(stockTicker);
	}

	public void saveWeeklyStock(WeeklyStock weeklyStock) {
		try {
			weeklyStockRepository.save(weeklyStock);
		}
		catch (Exception e) {
			throw new RuntimeException("fail to store weekly stock data: " + e.getMessage());
		}
	}
}
