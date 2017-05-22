package com.message.processor;

import com.message.helper.ToBeValidated;
import com.message.pojo.Entity;

public interface Processor {
	@ToBeValidated public void process(Entity entity);
	
	static Processor newProcessor() {
        ProcessorFactory processorFactory = new ProcessorFactory();
        return processorFactory.getInstance();
    }
}
