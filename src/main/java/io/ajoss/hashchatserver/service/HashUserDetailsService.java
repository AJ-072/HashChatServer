package io.ajoss.hashchatserver.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.ajoss.hashchatserver.model.HashUser;
import io.ajoss.hashchatserver.springRepository.UserRepository;

@Service
public class HashUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HashUser user = userRepository.findByusername(username);
		if (user == null)
			throw new UsernameNotFoundException("User Not Found");
		return new User(user.getUsername(), user.getPassword(),
				Collections.singleton(new SimpleGrantedAuthority("USER")));
	}
}
