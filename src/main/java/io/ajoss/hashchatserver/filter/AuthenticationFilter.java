package io.ajoss.hashchatserver.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.ajoss.hashchatserver.service.HashUserDetailsService;
import io.ajoss.hashchatserver.service.HashUserJwt;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	private String username;
	private String token;

	@Autowired
	private HashUserJwt userJwt;

	@Autowired
	private HashUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		System.out.println("AuthHeader : " + authHeader);
		if (authHeader != null && authHeader.startsWith("HashUserToken ")) {
			token = authHeader.substring(13);
			System.out.println("token : " + token);
			username = userJwt.exctractUserID(token);
			System.out.println("username : " + username);

		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			System.out.println("its working!");
			if (userJwt.validateToken(token, userDetails)) {
				System.out.println("userdetials " + userDetails.getUsername());
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						username, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);

	}

}
