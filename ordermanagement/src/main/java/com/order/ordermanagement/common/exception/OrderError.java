package com.order.ordermanagement.common.exception;

public enum OrderError implements ApiError{
	
	ORDER_NOT_FOUND(404, "order.notfound", "Order details not found", "Validation Failure"),
	ORDER_NOT_FOUND_CUSTOMER(404, "order.notfound", "No Orders were found for the customer", "Validation Failure"),
	ORDER_STATUS_ERROR(400,"Bad Request","Status cannot be updated","Validation Error"),
	ORDER_STATUS_INVALID(400,"Bad Request","Invalid Status","Validation Error");

	int httpStatus;
	String errorCode;
	String errorMessage;
	String errorType;
	
	OrderError(int httpStatus, String errorCode, String errorMessage, String errorType) {
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorType = errorType;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setDefaultMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}	
	
}
