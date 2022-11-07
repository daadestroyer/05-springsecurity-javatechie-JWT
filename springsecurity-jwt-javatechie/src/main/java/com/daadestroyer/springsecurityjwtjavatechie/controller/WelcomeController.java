package com.daadestroyer.springsecurityjwtjavatechie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daadestroyer.springsecurityjwtjavatechie.entity.JwtRequest;
import com.daadestroyer.springsecurityjwtjavatechie.entity.JwtResponse;
import com.daadestroyer.springsecurityjwtjavatechie.util.JwtUtil;

@RestController
public class WelcomeController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/")
	public String welcome() {
		return "Welcome to javatechie";
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println("JWT User Details = " + jwtRequest);

		// this line will validate the username and password if authentication is
		// success then we can generate token
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		} catch (Exception e) {
			throw new Exception("invalid username/password");
		}

		// if there is no exception just generate the token
		String generateToken = this.jwtUtil.generateToken(jwtRequest.getUsername());
		
		
		return ResponseEntity.ok(new JwtResponse(generateToken));
	}
}