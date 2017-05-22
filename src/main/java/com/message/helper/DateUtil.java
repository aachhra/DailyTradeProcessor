package com.message.helper;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.GregorianCalendar;

import com.message.pojo.Entity;

public class DateUtil {
	public static boolean isWorkWeekForCurrency(String currency, LocalDate settlementDate) {
		if ("SAR".equals(currency) || "AED".equals(currency)) {
			boolean test = EnumSet.of(DayOfWeek.SUNDAY,DayOfWeek.MONDAY, DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY)
					.contains(settlementDate.getDayOfWeek());
			return test;
		} else {
			boolean test = EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY)
					.contains(settlementDate.getDayOfWeek());
			return test;
		}
	}
	
	public static LocalDate getWorkingSettlementDate(Entity entity){
		LocalDate settleDate = entity.getSettlementDate();
		
		if(isWorkWeekForCurrency(entity.getCurrency(), entity.getSettlementDate())){
			return settleDate;
		}else{
			entity.setSettlementDate(settleDate.plusDays(1));
			return getWorkingSettlementDate(entity);
		}
	}
}