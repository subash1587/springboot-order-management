package com.order.ordermanagement.common.exception;

public class AppException extends RuntimeException{

	AppError appError;
	String message;
	
	public AppException(AppError appError) {
		this.appError = appError;
	}
	
	public AppException(AppError appError, String message) {
		this.appError = appError;
		this.message = message;
	}

	public AppError getAppError() {
		return appError;
	}

	public void setAppError(AppError appError) {
		this.appError = appError;
	}
}
