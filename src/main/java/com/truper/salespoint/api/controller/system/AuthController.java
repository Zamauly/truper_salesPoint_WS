package com.truper.salespoint.api.controller.system;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truper.salespoint.api.payload.request.*;
import com.truper.salespoint.api.payload.response.*;
import com.truper.salespoint.api.repository.system.ActiveUserRepository;
import com.truper.salespoint.api.repository.system.RoleRepository;
import com.truper.salespoint.api.util.JwtUtils;
import com.truper.salespoint.api.service.system.ActiveUserDetails;
import com.truper.salespoint.api.model.system.*;
import com.truper.salespoint.api.commons.Constants;
import com.truper.salespoint.api.exception.ResponseException;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	ActiveUserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	private static final Logger _log = LoggerFactory.getLogger(AuthController.class);
	
	@PostMapping("/signin")
	public ResponseEntity<ResponseModel<?>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		String newPassEnc = encoder.encode(loginRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

		_log.info(" this is a new pass encripted: "+newPassEnc);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		ActiveUserDetails userDetails = (ActiveUserDetails) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new ResponseModel<JwtResponse>("OK","Se ha autentificado Correctamente",new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 roles)));

	}
	
	@PostMapping("/signup")
	public ResponseEntity<ResponseModel<?>> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
		
			ResponseException responseExp = new ResponseException(Constants.validateException("UsedValueForEntity")," El nombre de usuario ya ha sido usado");
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>("ERROR","  Username is already taken! ",responseExp));

		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
		
			ResponseException responseExp = new ResponseException(Constants.validateException("UsedValueForEntity")," El email de usuario ya ha sido usado");
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>("ERROR","  Email is already taken! ",responseExp));
		}
		String newPassEnc = encoder.encode(signUpRequest.getPassword());
		_log.info(" this is a new pass encripted: "+newPassEnc);
		// Create new user's account
		ActiveUser user = new ActiveUser(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),newPassEnc );

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByRol(Constants.ValidRole.USER_ROLE)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByRol(Constants.ValidRole.ADMIN_ROLE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByRol(Constants.ValidRole.USER_ROLE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new ResponseModel<ActiveUser>("OK","Se ha cargado Correctamente",user));

	}
}
