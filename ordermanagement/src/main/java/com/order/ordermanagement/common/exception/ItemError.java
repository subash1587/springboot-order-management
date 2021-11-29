package com.order.ordermanagement.common.exception;

public enum ItemError implements ApiError {

	ITEM_NOT_FOUND(404,"Item.NotFound", "Item is not found", "Validation Error");
	
	int httpStatus;
	String errorCode;
	String errorMessage;
	String errorType;
	
	ItemError(int httpStatus, String errorCode, String errorMessage, String errorType){
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
