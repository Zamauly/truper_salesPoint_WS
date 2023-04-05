package com.truper.salespoint.api.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.truper.salespoint.api.exception.ResponseException;
import com.truper.salespoint.api.service.ResponseModel;
import com.truper.salespoint.api.commons.Constants;

import java.util.HashMap; 
import java.util.Map; 

@ControllerAdvice
public class ValidationAdvice {

	private static final Logger _log = LoggerFactory.getLogger(ValidationAdvice.class);
	
	  @ResponseBody
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  ResponseModel<Map<String,ResponseException>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		    Map<String, ResponseException> errors = new HashMap<>();
		    ex.getBindingResult().getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        _log.error(" FieldName: "+fieldName+" getClass.getName: "+error.getClass().getName()+" defaultMessage: "+errorMessage);
		        errors.put(fieldName,  new ResponseException(Constants.validateException(error.getClass().getName()), errorMessage));
		    });
	    return new ResponseModel<Map<String,ResponseException>>("ERROR"," Error de validacion. ",errors);
	  }
	  
}
