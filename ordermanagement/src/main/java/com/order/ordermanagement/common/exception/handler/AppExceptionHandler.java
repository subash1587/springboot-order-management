package com.order.ordermanagement.common.exception.handler;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class AppExceptionHandler {

	private MessageSource messageSource;
	
	public AppExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
}
