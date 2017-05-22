package com.message.helper;

import java.math.BigDecimal;

import com.message.enums.Indicator;
import com.message.exception.MessageException;
import com.message.pojo.Entity;

public class ValidationUtil {
	public void validate(Entity entity){
		if(entity == null){
			throw new MessageException("Trade Message is empty");
		}
		if(entity.getEntityName()== null || entity.getEntityName().length() == 0){
			throw new MessageException("Trade stock name missing");
		}
		if(entity.getFxRate().compareTo(new BigDecimal("0.01")) < 0){
			throw new MessageException("Invalid Forex Rate");
		}
		if(entity.getUnits() <= 0){
			throw new MessageException("Units cannot be 0 or less than 0");
		}
		if(!Indicator.BUY.equals(entity.getActionIndicator()) && !Indicator.SELL.equals(entity.getActionIndicator())){
			throw new MessageException("Unable to understand the action. Should be BUY or SELL");
		}
		if(entity.getInstructionDate().compareTo(entity.getSettlementDate()) > 0){
			throw new MessageException("Instruction Date cannot be after settlement date");
		}
		if(entity.getPricePerUnit().compareTo(new BigDecimal("0.01")) < 0){
			throw new MessageException("Invalid price per unit");
		}
	}
}
