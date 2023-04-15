package com.truper.salespoint.api.commons;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class Constants {
	
	public static final String _ERROR = "ERROR";
	public static final String _OK = "OK";
	
	public static final Map<String, String> BUSINESS_EXCEPTIONS_MSG = Map.ofEntries(
            Map.entry("NOT_FOUND","Error de existencia. "),
            Map.entry("NOT_ADD", "Error al cargar. "),
            Map.entry("NOT_UPDATE", "Error al actualizar. "),
            Map.entry("NOT_DELETE_PROCESS", "No Se ha eliminado Correctamente. "),
            Map.entry("NOT_DELETE_EXCEPTION", "Error al eliminar. ")
        );
	
	public static final Map<String, String> BUSINESS_MSG = Map.ofEntries(
            Map.entry("FOUND","Se ha listado correctamente. "),
            Map.entry("LOAD", "Se ha cargado Correctamente. "),
            Map.entry("DELETE", "Se ha eliminado Correctamente. ")
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
		    Arrays.asList("NotFoundException", "InventaryException", "MethodArgumentNotValidException","ViolationFieldError", "ConstraintViolationImpl", "AuthenticationException",
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
