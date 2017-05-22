package com.message.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.message.enums.Indicator;
import com.message.pojo.Entity;

public class InstructionsGenerator {
    public static Set<Entity> getInstructions() {
        return new HashSet<>(Arrays.asList(

        		new Entity(
        		"Entity1",
        		Indicator.BUY,
        		 new BigDecimal("0.50"),
        		"AED",
        		LocalDate.of(2017, 5, 10),
                LocalDate.of(2017, 5, 13),
        		50L,
        	    new BigDecimal("155.00")),
        	   

        		new Entity(
                		"Entity2",
                		Indicator.BUY,
                		 new BigDecimal("0.22"),
                		"AED",
                		LocalDate.of(2017, 3, 10),
                		LocalDate.of(2017, 3, 13),
                		10L,
                	    new BigDecimal("150.00"))

        		/*new Entity(
                		"Entity2",
                		Indicator.SELL,
                		 new BigDecimal("0.25"),
                		"SAR",
                		LocalDate.of(2017, 3, 15),
                		LocalDate.of(2017, 3, 19),
                		100L,
                	    new BigDecimal("250.00")),

        		new Entity(
                		"Entity3",
                		Indicator.BUY,
                		 new BigDecimal("0.32"),
                		"EUR",
                		LocalDate.of(2017, 3, 27),
                		LocalDate.of(2017, 3, 29),
                		450L,
                	    new BigDecimal("150.00")),

        		new Entity(
                		"Entity4",
                		Indicator.BUY,
                		 new BigDecimal("0.32"),
                		"EUR",
                		LocalDate.of(2017, 3, 05),
                		LocalDate.of(2017, 3, 15),
                		250L,
                	    new BigDecimal("325.00")),

        		new Entity(
                		"Entity5",
                		Indicator.SELL,
                		 new BigDecimal("0.25"),
                		"SAR",
                		LocalDate.of(2017, 3, 12),
                		LocalDate.of(2017, 3, 18),
                		100L,
                	    new BigDecimal("225.00")),

        		new Entity(
                		"Entity6",
                		Indicator.SELL,
                		 new BigDecimal("0.32"),
                		"EUR",
                		LocalDate.of(2017, 3, 11),
                		LocalDate.of(2017, 3, 17),
                		100L,
                	    new BigDecimal("150.00"))*/));
            
    }
}
