package com.truper.salespoint.api.advice;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.truper.salespoint.api.exception.NotFoundException;
import com.truper.salespoint.api.exception.ResponseException;
import com.truper.salespoint.api.payload.response.ResponseModel;
import com.truper.salespoint.api.commons.Constants;

@RestControllerAdvice
public class NotFoundAdvice {

	  @ResponseBody
	  @ExceptionHandler(NotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  ResponseModel<ResponseException> clienteNotFoundHandler(NotFoundException ex) {
		
		ResponseException responseExp = new ResponseException(Constants.validateException(ex.getClass().getName()),ex.getMessage());
	    return new ResponseModel<ResponseException>(Constants._ERROR,Constants.BUSINESS_EXCEPTIONS_MSG.get("NOT_FOUND"),responseExp);
	  }
	  
}
