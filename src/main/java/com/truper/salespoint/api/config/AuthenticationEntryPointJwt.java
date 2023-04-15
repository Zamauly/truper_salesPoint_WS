package com.truper.salespoint.api.config;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.truper.salespoint.api.commons.Constants;
import com.truper.salespoint.api.exception.ResponseException;
import com.truper.salespoint.api.payload.response.ResponseModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@Component
public class AuthenticationEntryPointJwt implements AuthenticationEntryPoint {

	private static final Logger _log = LoggerFactory.getLogger(AuthenticationEntryPointJwt.class);
    
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		_log.error("Unauthorized error: {} con nombred de superclase {} y nombre de clase {} ", authException.getMessage(),
				authException.getClass().getSuperclass().getName(),authException.getClass().getName());
		
		ResponseException responseExp = new ResponseException(Constants.validateException(authException.getClass().getSuperclass().getName()),authException.getMessage());	    
	    Gson gson = new Gson();
		
	    String userJsonString = gson.toJson(new ResponseModel<ResponseException>("ERROR"," Error de autenticacion. ",responseExp));
		response.setStatus( HttpServletResponse.SC_UNAUTHORIZED );
		
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userJsonString);
        out.flush();
        out.close();
        
	}
	
}
