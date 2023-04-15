package com.truper.salespoint.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import com.truper.salespoint.api.commons.Constants;
import com.truper.salespoint.api.filter.AuthenticationTokenFilter;
import com.truper.salespoint.api.service.system.ActiveUserDetailsServiceImpl;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.truper.salespoint.api.commons.Constants;

@Configuration
@EnableGlobalMethodSecurity(
	    // securedEnabled = true,
	    // jsr250Enabled = true,
	    prePostEnabled = true)
public class WebSecurityConfig {

	public static final AntPathRequestMatcher[] WHITE_LIST_URLS = {new AntPathRequestMatcher(Constants.ALLOWED_PATHS.get("TEST")),
																														new AntPathRequestMatcher(Constants.ALLOWED_PATHS.get("AUTH"))};
	
	public static final AntPathRequestMatcher[] IGNORE_LIST_URLS = {new AntPathRequestMatcher(Constants.ALLOWED_PATHS.get("H2_CONSOLE")),
																														new AntPathRequestMatcher(Constants.ALLOWED_PATHS.get("JS")),
																														new AntPathRequestMatcher(Constants.ALLOWED_PATHS.get("IMAGES"))};
	
	  @Autowired
	  ActiveUserDetailsServiceImpl userDetailsService;

	  @Autowired
	  private AuthenticationEntryPointJwt unauthorizedHandler;
	  
	  @Bean
	  AuthenticationTokenFilter authenticationJwtTokenFilter() {
	    return new AuthenticationTokenFilter();
	  }
	  
	  @Bean
	  DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService((UserDetailsService) userDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }
	  
	  @Bean
	  AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }
	  
	  @Bean
	  PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	  
	  @Bean
	  WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().requestMatchers(IGNORE_LIST_URLS); 
	  }
	  
	  @Bean
	   SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		  
		    http
		    		.cors()
		    		.and()
		    		.csrf()
		    		.disable()
		    		.exceptionHandling()
		    		.authenticationEntryPoint(unauthorizedHandler)
		    		.and()
		    		.sessionManagement()
		    		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    		.and()
		    		.authorizeHttpRequests()
		    		.requestMatchers(WHITE_LIST_URLS)
		    		.permitAll()
		    		.anyRequest()
		    		.authenticated();
		    
		    http.authenticationProvider(authenticationProvider());
	
		    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		    
		    return http.build();
	  }

}
