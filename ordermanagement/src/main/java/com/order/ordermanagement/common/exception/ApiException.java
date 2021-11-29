package com.order.ordermanagement.common.exception;

public class ApiException extends RuntimeException{
		
	private ApiError apiError;
	
	public ApiException(ApiError apiError) {
		this.apiError = apiError;	
	}
	public int getHttpStatus() {
		return apiError.getHttpStatus();
	}
	public String getErrorCode() {
		return apiError.getErrorCode();
	}
	public String getErrorMessage() {
		return apiError.getErrorMessage();
	}
	public String getErrorType() {
		return apiError.getErrorType();
	}
}
