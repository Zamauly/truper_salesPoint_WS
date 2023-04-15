package com.truper.salespoint.api.commons;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class Constants {
	
	public static final String _ERROR = "ERROR";
	public static final String _OK = "OK";
	
	public static final Map<String, String> BUSINESS_EXCEPTIONS = Map.ofEntries(
            Map.entry("NOT_FOUND","NotFoundException"),
            Map.entry("AUTH", "/api/auth/**"),
            Map.entry("H2_CONSOLE", "/h2-console/***"),
            Map.entry("JS", "/js/***"),
            Map.entry("IMAGES", "/images/***")
        );
	
	public static final Map<String, String> ALLOWED_PATHS = Map.ofEntries(
            Map.entry("TEST","/api/test/**"),
            Map.entry("AUTH", "/api/auth/**"),
            Map.entry("H2_CONSOLE", "/h2-console/***"),
            Map.entry("JS", "/js/***"),
            Map.entry("IMAGES", "/images/***")
        );
	
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
