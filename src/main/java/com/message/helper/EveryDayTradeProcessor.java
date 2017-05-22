package com.message.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.message.enums.Indicator;
import com.message.pojo.Entity;
import com.message.processor.Processor;

public class EveryDayTradeProcessor implements Processor{

	private static EveryDayTradeProcessor INSTANCE = new EveryDayTradeProcessor();

	private Map<LocalDate, BigDecimal> incomingSettlementMap = new HashMap<LocalDate, BigDecimal>();

	private Map<LocalDate, BigDecimal> outgoingSettlementMap = new HashMap<LocalDate, BigDecimal>();

	public static EveryDayTradeProcessor getInstance() {
		return INSTANCE;
	}

	public void process(Entity entity) {
		if (Indicator.SELL.equals(entity.getActionIndicator())) {
			setIncomingSettlement(entity.getSettlementDate(), entity.getPriceInUSD());
		} else if (Indicator.BUY.equals(entity.getActionIndicator())) {
			setOutgoingSettlement(entity.getSettlementDate(), entity.getPriceInUSD());
		}
	}

	private void setIncomingSettlement(LocalDate settlementDate, BigDecimal settlementAmount) {
		if (incomingSettlementMap.get(settlementDate) != null) {
			BigDecimal oldSettlementAmount = incomingSettlementMap.get(settlementDate);
			BigDecimal newSettlementAmount = oldSettlementAmount.add(settlementAmount);
			incomingSettlementMap.put(settlementDate, newSettlementAmount);
		} else {
			incomingSettlementMap.put(settlementDate, settlementAmount);
		}
	}

	private void setOutgoingSettlement(LocalDate settlementDate, BigDecimal settlementAmount) {
		if (outgoingSettlementMap.get(settlementDate) != null) {
			BigDecimal oldSettlementAmount = outgoingSettlementMap.get(settlementDate);
			BigDecimal newSettlementAmount = oldSettlementAmount.add(settlementAmount);
			outgoingSettlementMap.put(settlementDate, newSettlementAmount);
		} else {
			outgoingSettlementMap.put(settlementDate, settlementAmount);
		}
	}

	public Map<LocalDate, BigDecimal> getTodaysIncomingAggregate() {
		return incomingSettlementMap;
	}

	public Map<LocalDate, BigDecimal> getTodaysOutgoingAggregate() {
		return outgoingSettlementMap;
	}
}
