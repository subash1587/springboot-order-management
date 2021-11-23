package com.order.ordermanagement.common.exception;

public enum OrderError implements AppError{
	
	ORDER_NOT_FOUND("order.notfound", "Order details not found", SeverityType.NONE, ErrorType.VALIDATION_FAILURE);
	
	String errorCode;
	String defaultMessage;
	SeverityType severityType;
	ErrorType errorType;
	
	OrderError(String errorCode, String defaultMessage, SeverityType severityType, ErrorType errorType) {
		this.errorCode = errorCode;
		this.defaultMessage = defaultMessage;
		this.severityType = severityType;
		this.errorType = errorType;
	}

	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getDefaultMessage() {
		return defaultMessage;
	}

	@Override
	public SeverityType getSeverityType() {
		return severityType;
	}

	@Override
	public ErrorType getErrorType() {
		return errorType;
	}
	
}
