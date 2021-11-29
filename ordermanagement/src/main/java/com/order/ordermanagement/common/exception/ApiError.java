package com.order.ordermanagement.common.exception;

public interface ApiError {
	
	int getHttpStatus();
	String getErrorCode();
	String getErrorMessage();
	String getErrorType();

}
