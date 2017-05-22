package com.message.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


import com.message.helper.MessageProcessor;
import com.message.helper.ToBeValidated;
import com.message.helper.ValidationUtil;
import com.message.pojo.Entity;


public class ValidatingInterceptor implements InvocationHandler{
	
	private MessageProcessor messageProcessor;
	
	public ValidatingInterceptor(MessageProcessor messageProcessor){
		this.messageProcessor = messageProcessor;
	}

	  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		  if(method.isAnnotationPresent(ToBeValidated.class)){
		  ValidationUtil util = new ValidationUtil();
		  util.validate((Entity)args[0]);
		  }
          return method.invoke(this.messageProcessor, args);
      }

//	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
//		ValidationUtil utils = new ValidationUtil();
//		
//		System.out.println("Called Inceptor");
//		
//		arg3.
//		return null;
//	}

}
