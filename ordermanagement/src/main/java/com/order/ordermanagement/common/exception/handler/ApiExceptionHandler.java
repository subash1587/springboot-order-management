package com.order.ordermanagement.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.order.ordermanagement.common.exception.ApiException;
import com.order.ordermanagement.common.exception.AppError;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(ApiException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<?> sendError(ApiException ex) {
		AppError error = new AppError();
		error.setErrorCode(ex.getErrorCode());
		error.setErrorMessage(ex.getErrorMessage());
		error.setErrorType(ex.getErrorType());
		error.setHttpStatus(ex.getHttpStatus());
		return ResponseEntity.ok(error);
	}
}
