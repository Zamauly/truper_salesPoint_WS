package com.truper.salespoint.api.commons;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Constants {
	
	public static enum ValidRole {
		USER_ROLE,
		ADMIN_ROLE
	}
	
	public static final ArrayList<String> REQUEST_EXCEPTIONS = new ArrayList<String>(
		    Arrays.asList("ClienteNotFound", "ProductoNotFound", "ListaCompraNotFound", "ListaDetalleNotFound", "InventaryException",
		    		"MethodArgumentNotValidException","ViolationFieldError", "ConstraintViolationImpl", "AuthenticationException",
		    		"UsedValueForEntity"));
    
	public final static String validateException(String exceptionName) {
		Iterator<String> requestExceptions = REQUEST_EXCEPTIONS.iterator();
		String errorType = "system";
		while(requestExceptions.hasNext()) {
			if(exceptionName.contains(requestExceptions.next())) {
				errorType = "request";
				break;
			}
		}
		
		return errorType;
	}
}
