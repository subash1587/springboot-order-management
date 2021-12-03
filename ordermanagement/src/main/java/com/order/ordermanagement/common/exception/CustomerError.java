package com.order.ordermanagement.common.exception;

public enum CustomerError implements ApiError{

	CUSTOMER_NOT_FOUND(404, "Customer.Notfound", "Customer is not found", "Validation Failure"),
	CUSTOMER_INVALID_SORT_KEY(400, "Customer.InvalidSortKey", "Sort parameter isnotvalid", "Validation Failure");
	
	int httpStatus;
	String errorCode;
	String errorMessage;
	String errorType;
	
	CustomerError(int httpStatus, String errorCode, String errorMessage, String errorType){
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorType = errorType;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorType() {
		return errorType;
	}
}
