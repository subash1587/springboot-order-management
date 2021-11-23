package com.order.ordermanagement.common.exception;

public interface AppError {

	String getErrorCode();
	String getDefaultMessage();
	SeverityType getSeverityType();
	ErrorType getErrorType();
}
