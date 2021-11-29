package com.order.ordermanagement.common.exception;

public enum CustomerAddressError implements ApiError{

	CUSTOMER_ADDRESS_NOT_FOUND(404,"NotFound","No address is found for the customer","Validation Error"),
	CUSTOMER_ADDRESS_TYPE_NOT_FOUND(404,"NotFound","Customer Address type is not found","Validation Error");
	
	int httpStatus;
	String errorCode;
	String errorMessage;
	String errorType;
	
	CustomerAddressError(int httpStatus, String errorCode, String errorMessage, String errorType){
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
