package com.truper.salespoint.api.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.dao.DataIntegrityViolationException;

import com.truper.salespoint.api.exception.ResponseException;
import com.truper.salespoint.api.payload.response.ResponseModel;
import com.truper.salespoint.api.commons.Constants;

import jakarta.validation.ConstraintViolationException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationAdvice {

	private static final Logger _log = LoggerFactory.getLogger(ValidationAdvice.class);

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseModel<Map<String, ResponseException>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, ResponseException> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			_log.error(" FieldName: " + fieldName + " getClass.getName: " + error.getClass().getName()
					+ " defaultMessage: " + errorMessage);
			errors.put(fieldName,
					new ResponseException(Constants.validateException(error.getClass().getName()), errorMessage));
		});
		return new ResponseModel<Map<String, ResponseException>>("ERROR", " Error de validacion. ", errors);
	}

	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseModel<Map<String, ResponseException>> handleValidationExceptions(ConstraintViolationException ex) {

		Map<String, ResponseException> errors = new HashMap<>();
		ex.getConstraintViolations().forEach((error) -> {
			_log.error(" Error at constraints Name: " + error.getClass().getName() + " Message: " + error.getMessage()
					+ " Path: " + error.getPropertyPath());
			errors.put(error.getPropertyPath().toString(),
					new ResponseException(Constants.validateException(error.getClass().getName()), error.getMessage()));
		});

		return new ResponseModel<Map<String, ResponseException>>("ERROR", " Error de validacion. ", errors);
	}

	 @ResponseBody
	 @ExceptionHandler(HttpMessageNotReadableException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 ResponseModel<ResponseException> handleValidationHttpExceptions(HttpMessageNotReadableException ex) {
		 //Map<String, ResponseException> errors = new HashMap<>();

		 _log.error(" Error at constraints Name: "+ex.getClass().getName()+" Message: "+ex.getMessage()+" getHttpInputMessage: "+ex.getHttpInputMessage());
		 ResponseException error = new ResponseException(Constants.validateException(ex.getClass().getName()), ex.getMessage()); 
		 return new	  ResponseModel<ResponseException>("ERROR"," Error de validacion. " ,error); 
	  }
	 
	 @ResponseBody
	 @ExceptionHandler(DataIntegrityViolationException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 ResponseModel<ResponseException> handleValidationIntegrityExceptions(DataIntegrityViolationException ex) {
		// Map<String, ResponseException> errors = new HashMap<>();

		 _log.error(" Error at constraints Name: "+ex.getClass().getName()+" Message: "+ex.getMessage()+" getLocalizedMessage: "+ex.getLocalizedMessage());
		 ResponseException error = new ResponseException(Constants.validateException(ex.getClass().getName()), ex.getMessage()); 
		 return new	  ResponseModel<ResponseException>("ERROR"," Error de validacion. " ,error); 
	  }
}
