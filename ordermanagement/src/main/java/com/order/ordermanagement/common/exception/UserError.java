package com.order.ordermanagement.common.exception;

public enum UserError implements ApiError{
	USER_EXISTS(409,"User.Duplicate","UserName already exists","Validation Failure");
	
	int httpStatus;
	String errorCode;
	String errorMessage;
	String errorType;
	
	UserError(int httpStatus, String errorCode, String errorMessage, String errorType){
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorType = errorType;
	}
	
	@Override
	public int getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String getErrorType() {
		return errorType;
	}

}
