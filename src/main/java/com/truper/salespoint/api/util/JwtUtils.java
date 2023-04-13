package com.truper.salespoint.api.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.truper.salespoint.api.service.system.ActiveUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	private static final Logger _log = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${truper.salespoint.api.jwtSecret}")
	private String jwtSecret;

	@Value("${truper.salespoint.api.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {

		ActiveUserDetails userPrincipal = (ActiveUserDetails) authentication.getPrincipal();

		_log.info(" this is a : jwtSecret"+jwtSecret);
		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			_log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			_log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			_log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			_log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			_log.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
