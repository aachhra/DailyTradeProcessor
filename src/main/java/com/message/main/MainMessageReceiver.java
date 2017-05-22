package com.message.main;

import java.util.Set;

import com.message.helper.EveryDayTradeProcessor;
import com.message.helper.InstructionsGenerator;
import com.message.helper.ReportProcessor;
import com.message.pojo.Entity;
import com.message.processor.Processor;

public class MainMessageReceiver {
	public static void main(String args[]){
		
		// Get Trade instruction messages
		final Set<Entity> instructions = InstructionsGenerator.getInstructions();
		
		// processes incoming and outgoing entity based on currency and working day. Then Ranks them
		Processor processor =   Processor.newProcessor();		
		instructions.forEach(processor::process);	
		
		//calculates the incoming and outgoing settled amount
		Processor everyDayTradeProcessor = EveryDayTradeProcessor.getInstance();
		instructions.forEach(everyDayTradeProcessor::process);		
		
		//Log report
		ReportProcessor reportProcessor = new ReportProcessor();
		reportProcessor.process();
	}
}
