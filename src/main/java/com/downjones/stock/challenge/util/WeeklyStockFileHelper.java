package com.downjones.stock.challenge.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.downjones.stock.challenge.domain.WeeklyStock;
import com.downjones.stock.challenge.domain.WeeklyStockId;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class WeeklyStockFileHelper {

	public static List<WeeklyStock> csvToWeeklyStock(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			 CSVParser csvParser = new CSVParser(fileReader,
					 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<WeeklyStock> weeklyStockList = new ArrayList<>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			SimpleDateFormat textFormat = new SimpleDateFormat("dd/MM/yyyy");

			for (CSVRecord csvRecord : csvRecords) {
				WeeklyStockId weeklyStockId = new WeeklyStockId(
						Integer.valueOf(csvRecord.get("quarter")),
						csvRecord.get("stock"),
						textFormat.parse(csvRecord.get("date")));

				WeeklyStock weeklyStock = new WeeklyStock(
						weeklyStockId,
						parseDoubleValue(csvRecord.get("open")),
						parseDoubleValue(csvRecord.get("high")),
						parseDoubleValue(csvRecord.get("low")),
						parseDoubleValue(csvRecord.get("close")),
						parseIntegerValue(csvRecord.get("volume")),
						parseDoubleValue(csvRecord.get("percent_change_price")),
						parseDoubleValue(csvRecord.get("percent_change_volume_over_last_wk")),
						parseIntegerValue(csvRecord.get("previous_weeks_volume")),
						parseDoubleValue(csvRecord.get("next_weeks_open")),
						parseDoubleValue(csvRecord.get("next_weeks_close")),
						parseDoubleValue(csvRecord.get("percent_change_next_weeks_price")),
						parseIntegerValue(csvRecord.get("days_to_next_dividend")),
						parseDoubleValue(csvRecord.get("percent_return_next_dividend"))
				);

				weeklyStockList.add(weeklyStock);
			}

			return weeklyStockList;
		}
		catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
		catch (ParseException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	private static double parseDoubleValue(String value) {
		return value.isEmpty() ? 0 : Double.parseDouble(value.replace("$", ""));
	}

	private static int parseIntegerValue(String value) {
		return value.isEmpty() ? 0 : Integer.valueOf(value);
	}
}
