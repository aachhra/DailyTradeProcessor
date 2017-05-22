package com.message.processor;

import java.lang.reflect.Proxy;

import com.message.helper.MessageProcessor;
import com.message.interceptor.ValidatingInterceptor;

public class ProcessorFactory {
	public Processor getInstance(){
		 return (Processor) Proxy.newProxyInstance(
	             this.getClass().getClassLoader(),
	             new Class[]{Processor.class},
	             new ValidatingInterceptor(new MessageProcessor()));
		}
}
