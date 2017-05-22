package com.message.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.message.comparator.MessageComparator;
import com.message.enums.Indicator;
import com.message.pojo.Entity;
import com.message.processor.Processor;


public class MessageProcessor implements Processor {
	
	public MessageProcessor() {
		// TODO Auto-generated constructor stub
	}
	
	public static MessageProcessor getInstance(){
		return INSTANCE;
	}
	
	private static final MessageProcessor INSTANCE = new MessageProcessor();
	
    public static List<Entity> outgoingEntities = new ArrayList<Entity>();
	
	public static List<Entity> incomingEntities = new ArrayList<Entity>();
	
	MessageComparator comparator = new MessageComparator();
	
	
	@ToBeValidated									//ValidationUtil called to validate if entity is perfect
	public void process(Entity entity){
		
		BigDecimal totalPrice = getTotalPrice(entity);
		
		entity.setPriceInUSD(getPriceInUSD(totalPrice, entity));
		
		
		
		 //check if falling on weekend .Adjust settlement date to next working day
		entity.setSettlementDate(DateUtil.getWorkingSettlementDate(entity)); 		
		
		if(Indicator.SELL.equals(entity.getActionIndicator())){
			if(incomingEntities.contains(entity)){		//if the list already contains the trade entity, update the entity
				updateEntity(incomingEntities, entity);
			}else{
				incomingEntities.add(entity);
			}
		}else{
			if(outgoingEntities.contains(entity)){
				updateEntity(outgoingEntities, entity);
			}else{
				outgoingEntities.add(entity);
			}
		}
		// apply ranking based on USD price
		Collections.sort(outgoingEntities,comparator);
		Collections.sort(incomingEntities,comparator);
	}
	
	private BigDecimal getTotalPrice(Entity entity){
		return new BigDecimal(entity.getUnits()).multiply(entity.getPricePerUnit());
	}
	
	private BigDecimal getPriceInUSD(BigDecimal totalPrice,Entity entity){
		return totalPrice.multiply(entity.getFxRate()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	private void updateEntity(List<Entity> entityList, Entity entity){
		int index = entityList.indexOf(entity);
		Entity oldEntity = entityList.get(index);
		oldEntity.setUnits(oldEntity.getUnits()+ entity.getUnits());
		oldEntity.setPriceInUSD(oldEntity.getPriceInUSD().add(entity.getPriceInUSD()));
		entityList.remove(index);
		entityList.add(oldEntity);
	}
}
