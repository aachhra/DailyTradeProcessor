package com.message.exception;

public class MessageException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 15938573967359L;

	public MessageException(){
		super();
	}
	
	public MessageException(String message){
		super(message);
	}
}
