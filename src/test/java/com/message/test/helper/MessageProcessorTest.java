package com.message.test.helper;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.message.enums.Indicator;
import com.message.helper.DateUtil;
import com.message.helper.MessageProcessor;
import com.message.pojo.Entity;

public class MessageProcessorTest {
	
	private MessageProcessor instance;
	
	@Before
    public void setUp() throws Exception {
		instance= MessageProcessor.getInstance();
    }
	
	@Test
    public void testTotalPriceCalc() throws Exception {
        final BigDecimal agreedFx = BigDecimal.valueOf(0.50);
        final BigDecimal pricePerUnit = BigDecimal.valueOf(100.25);
        final int units = 200;

        final Entity message = new Entity(
        		"foo",
        		Indicator.BUY,
        		 new BigDecimal("0.50"),
        		"AED",
        		LocalDate.of(2017, 5, 10),
                LocalDate.of(2017, 5, 13),
        		200L,
        	    new BigDecimal("100.25"));

        

        final BigDecimal correct = pricePerUnit
                                    .multiply(agreedFx)
                                    .multiply(BigDecimal.valueOf(units)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        
        instance.process(message);
                                    
        assertEquals(correct, message.getPriceInUSD());
    }
	
	@Test
	public void checkIfDateFallsOnWeekend(){
		LocalDate date= LocalDate.of(2017, 05, 14); //this is Sunday  ( for AED, working days- Sunday to thursaday)
		assertEquals(DateUtil.isWorkWeekForCurrency("AED", date),true);
	}
	
	@Test
	public void testNextWorkingSettlementDateIfHoliday(){
		
		LocalDate settlementDate = LocalDate.of(2017, 05, 14); // this is Sunday(working day for AED)
		
		final Entity message = new Entity(
        		"foo",
        		Indicator.BUY,
        		 new BigDecimal("0.50"),
        		"AED",
        		LocalDate.of(2017, 5, 10),
                LocalDate.of(2017, 5, 12), // this is Friday(non-working for AED)
        		200L,
        	    new BigDecimal("100.25"));

		assertEquals(settlementDate, DateUtil.getWorkingSettlementDate(message));
		
	}
}
