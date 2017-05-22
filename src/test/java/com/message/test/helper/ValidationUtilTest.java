package com.message.test.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.message.enums.Indicator;
import com.message.exception.MessageException;
import com.message.helper.ValidationUtil;
import com.message.pojo.Entity;

public class ValidationUtilTest {

	private Entity message;
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Before
	public void setUp(){
		 message = new Entity(
        		"foo",
        		Indicator.BUY,
        		 new BigDecimal("0.50"),
        		"AED",
        		LocalDate.of(2017, 5, 10),
                LocalDate.of(2017, 5, 12), // this is Friday(non-working for AED)
        		200L,
        	    new BigDecimal("100.25"));
	}
	
	@Test(expected = MessageException.class)
	public void checkForEmptyName() {
		message.setEntityName("");
		ValidationUtil util = new ValidationUtil();
		util.validate(message);
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Trade Message is empty");
	}
	
	//similar tests can be performed for other validations too
}
