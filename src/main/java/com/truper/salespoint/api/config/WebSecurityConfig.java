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

import com.truper.salespoint.api.filter.AuthenticationTokenFilter;
import com.truper.salespoint.api.service.system.ActiveUserDetailsServiceImpl;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(
	    // securedEnabled = true,
	    // jsr250Enabled = true,
	    prePostEnabled = true)
public class WebSecurityConfig {

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
	    return (web) -> web.ignoring().requestMatchers("/js/**", "/images/**"); 
	  }
	  
	  @Bean
	   SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		  
		    http.cors().and().csrf().disable().exceptionHandling()
		    		.authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
		    		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		    		.authorizeRequests().requestMatchers("/api/auth/**").permitAll()
		    		.anyRequest().authenticated();
		    
		    http.authenticationProvider(authenticationProvider());
	
		    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		    
		    return http.build();
	  }

}