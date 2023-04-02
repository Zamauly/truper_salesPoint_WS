package com.truper.salespoint.api.advice;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.truper.salespoint.api.exception.ClienteNotFoundException;
import com.truper.salespoint.api.exception.ProductoNotFoundException;
import com.truper.salespoint.api.exception.ListaCompraNotFoundException;
import com.truper.salespoint.api.exception.ListaDetalleNotFoundException;
import com.truper.salespoint.api.exception.ResponseException;
import com.truper.salespoint.api.commons.Constants;

@ControllerAdvice
public class NotFoundAdvice {

	  @ResponseBody
	  @ExceptionHandler(ClienteNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  ResponseException clienteNotFoundHandler(ClienteNotFoundException ex) {
		
		ResponseException responseExp = new ResponseException(Constants.validateException(ex.getClass().getName()),ex.getMessage());
	    return responseExp;
	  }
	  
	  @ResponseBody
	  @ExceptionHandler(ProductoNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  ResponseException productoNotFoundHandler(ProductoNotFoundException ex) {
		
		ResponseException responseExp = new ResponseException(Constants.validateException(ex.getClass().getName()),ex.getMessage());
	    return responseExp;
	  }
	  
	  @ResponseBody
	  @ExceptionHandler(ListaCompraNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  ResponseException listaCompraNotFoundHandler(ListaCompraNotFoundException ex) {
		
		ResponseException responseExp = new ResponseException(Constants.validateException(ex.getClass().getName()),ex.getMessage());
	    return responseExp;
	  }
	  
	  @ResponseBody
	  @ExceptionHandler(ListaDetalleNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  ResponseException listaDetalleNotFoundHandler(ListaDetalleNotFoundException ex) {
		
		ResponseException responseExp = new ResponseException(Constants.validateException(ex.getClass().getName()),ex.getMessage());
	    return responseExp;
	  }
}