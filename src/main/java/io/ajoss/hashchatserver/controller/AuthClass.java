package io.ajoss.hashchatserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ajoss.hashchatserver.model.*;
import io.ajoss.hashchatserver.service.*;
import io.ajoss.hashchatserver.springRepository.*;

@RestController
@RequestMapping("/api/auth")
public class AuthClass {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private HashUserDetailsService userDetailsService;

	@Autowired
	private HashUserJwt hashUserJwt;

	@Autowired
	UserRepository userRepository;

	@PostMapping(value = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> Login(@RequestBody LoginRequestBody body) throws Exception {
		System.out.println(body.getUsername() + " : " + body.getPassword());
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword()));
		} catch (BadCredentialsException e) {
			System.out.println(e.fillInStackTrace());
			throw new Exception("Incorrect username or password", e);
		}
		System.out.println("username " + body.getPassword());
		final UserDetails user = userDetailsService.loadUserByUsername(body.getUsername());

		final String tokenString = hashUserJwt.GenerateToken(user);

		return ResponseEntity.ok("token : " + tokenString);
	}

	@PostMapping(value = "/signup", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> Signup(@RequestBody HashUser body) throws Exception {
		HashUser user;
		if (!userRepository.existsById(body.getUserID())) {
			body.setPassword(new BCryptPasswordEncoder().encode(body.getPassword()));
			user = userRepository.save(body);
		} else {
			return ResponseEntity.badRequest().body("User Already Exist");
		}
		return ResponseEntity.ok(user);
	}

	@RequestMapping("/logout")
	public String Logout(@RequestHeader("token") String token) {
		return token;
	}
}
