package com.order.ordermanagement.common.exception;

public enum ItemError implements ApiError {

	ITEM_NAME_LENGTH_EXCEEDS(400,"Item.BadRequest", "Length of item name exceeds the limit", "Validation Error"),
	ITEM_NAME_NULL(400,"Item.BadRequest", "Item name should not be null", "Validation Error"),
	ITEM_NAME_BLANK(400,"Item.BadRequest", "Item name should not be blank", "Validation Error"),
	ITEM_CATEGORY_LENGTH_EXCEEDS(400,"Item.BadRequest", "Length of category exceeds the limit", "Validation Error"),
	ITEM_RATING_INVALID(400,"Item.BadRequest", "Rating of the item must be within 0 to 5", "Validation Error"),
	ITEM_PRICE_INVALID(400,"Item.BadRequest", "Price of an item must be greater than or equal to zero", "Validation Error"),
	ITEM_BAD_REQUEST(400,"Item.BadRequest", "Invalid Request Parameter passed", "Validation Error"),
	ITEM_NOT_FOUND(404,"Item.NotFound", "Item is not found", "Validation Error"),
	ITEM_INVALID_SORT_PARAMETER(404,"Item.BadRequest", "Requested sort_by parameter is invalid", "Validation Error");
	
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
