package com.downjones.stock.challenge.controller;

import java.util.List;

import com.downjones.stock.challenge.domain.GenericResponse;
import com.downjones.stock.challenge.domain.WeeklyStock;
import com.downjones.stock.challenge.service.WeeklyStockService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("weekly-stock")
@RequiredArgsConstructor
public class WeeklyStockController {
	private final WeeklyStockService weeklyStockService;

	@PostMapping(value = "/upload", produces = "application/json")
	public ResponseEntity<GenericResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			weeklyStockService.save(file);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(message));
		}
		catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new GenericResponse(message));
		}
	}

	@GetMapping(value = "/find/{stockTicker}", produces = "application/json")
	public ResponseEntity<List<WeeklyStock>> getWeeklyStockById(@PathVariable("stockTicker") String stockTicker) {
		return ResponseEntity.status(HttpStatus.OK).body(weeklyStockService.findWeeklyStockByTicker(stockTicker));
	}

	@PostMapping(value = "add", produces = "application/json")
	public ResponseEntity<WeeklyStock> addWeeklyStock(@RequestBody WeeklyStock weeklyStock) {
		try {
			weeklyStockService.saveWeeklyStock(weeklyStock);
			return ResponseEntity.status(HttpStatus.OK).body(weeklyStock);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}
}
