# Requirements
## Upload bulk of data
Using parameter file to select the file to be used
POST - http://localhost:8080/weekly-stock/upload

## Query Stock by stock ticker
http://localhost:8080/weekly-stock/find/{stockTicker}

## Add a new record
http://localhost:8080/weekly-stock/add
```
{
	"weeklyStockId": {
		"quarter": 2,
		"stockId": "AA",
		"date": "2011-06-05T04:00:00.000+00:00"
	},
	"openPrice": 17.27,
	"highPrice": 17.96,
	"lowPrice": 16.83,
	"closePrice": 17.15,
	"volume": 225053559,
	"percentagePriceChange": -0.694847,
	"percentageVolumeChange": 147.7693094,
	"previousWeekVolume": 90831895,
	"nextWeekOpenPrice": 17.16,
	"nextWeekClosePrice": 17.1,
	"nextWeekPercentagePriceChange": -0.34965,
	"nextWeekDaysToNextDividend": 6,
	"percentageReturnNextDividend": 0.174927
}
```

# Configuration
## JVM
Java11
## DB
H2
+ URL = jdbc:h2:mem:testdb
+ User = sa
+ Password = password

# Design
A three layers application was developed for the requirements listed. 

Controller (WeeklyStockController) -> Service (WeeklyStockService) -> Repository (WeeklyStockCustomRepository - WeeklyStockRepository)

The same objects are using for the DB and Controller representation.

## Future work
1. Include unit tests for all the layers
2. Create value objects to separate the DB representation
3. Create enum to map valid values for the stock ticker
4. Add validations for the permitted values in the post using VO