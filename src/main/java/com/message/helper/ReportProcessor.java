package com.message.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map.Entry;

import com.message.pojo.Entity;

public class ReportProcessor  {
	public void process(){
		EveryDayTradeProcessor everyDayProcessor = EveryDayTradeProcessor.getInstance();
		
		
		System.out.println("-----------------------------------INCOMING INSTRUCTIONS RANKING------------------------------------");
		for(int i=0 ; i < MessageProcessor.incomingEntities.size() ; i++){
			Entity incomingEntity = MessageProcessor.incomingEntities.get(i);
			System.out.println("Date: "+ incomingEntity.getSettlementDate() + "   Rank "+ (int)(i+1) + "   Name:"+incomingEntity.getEntityName());
		}
		System.out.println("-----------------------------------OUTGOING INSTRUCTIONS RANKING------------------------------------");
		for(int i=0 ; i < MessageProcessor.outgoingEntities.size() ; i++){
			Entity outgoingEntity = MessageProcessor.outgoingEntities.get(i);
			System.out.println("Date: "+ outgoingEntity.getSettlementDate() + "   Rank "+ (int)(i+1) + "   Name:"+outgoingEntity.getEntityName());
		}
		System.out.println("-----------------------------------OUTGOING MESSAGES DAYWISE SETTLEMENT------------------------------------");
		for(Entry<LocalDate, BigDecimal> entry : everyDayProcessor.getTodaysOutgoingAggregate().entrySet()){
			System.out.println("Date:"+entry.getKey()+"--------------"+"Amount Settled:"+entry.getValue());
		}
		System.out.println("-----------------------------------INCOMING MESSAGES DAYWISE SETTLEMENT------------------------------------");
		for(Entry<LocalDate, BigDecimal> entry : everyDayProcessor.getTodaysIncomingAggregate().entrySet()){
			System.out.println("Date:"+entry.getKey()+"--------------"+"Amount Settled:"+entry.getValue());
		}
	}
}
