package com.truper.salespoint.api.service.system;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ActiveUserDetailsService {
	
	ActiveUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
