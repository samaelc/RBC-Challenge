package com.downjones.stock.challenge.repository;

import com.downjones.stock.challenge.domain.WeeklyStock;
import com.downjones.stock.challenge.domain.WeeklyStockId;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyStockRepository extends CrudRepository<WeeklyStock, WeeklyStockId> {
}
