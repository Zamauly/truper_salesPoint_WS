package com.truper.salespoint.api.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.truper.salespoint.api.model.system.ActiveUser;
import com.truper.salespoint.api.repository.system.ActiveUserRepository;

import jakarta.transaction.Transactional;

@Service
public class ActiveUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	ActiveUserRepository userRepository;

	@Override
	@Transactional
	public ActiveUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ActiveUser user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return ActiveUserDetails.build(user);
	}

}
