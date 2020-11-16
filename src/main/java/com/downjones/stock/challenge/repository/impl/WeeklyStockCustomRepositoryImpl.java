package com.downjones.stock.challenge.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.downjones.stock.challenge.domain.WeeklyStock;
import com.downjones.stock.challenge.repository.WeeklyStockCustomRepository;

import org.springframework.stereotype.Repository;

@Repository
public class WeeklyStockCustomRepositoryImpl implements WeeklyStockCustomRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<WeeklyStock> findWeeklyStocksByStockTicker(final String stockTicker) {
		return em
				.createQuery("select o from com.downjones.stock.challenge.domain.WeeklyStock o where o.weeklyStockId.stockId=:stockTicker",
						WeeklyStock.class).setParameter("stockTicker", stockTicker).getResultList();
	}
}
