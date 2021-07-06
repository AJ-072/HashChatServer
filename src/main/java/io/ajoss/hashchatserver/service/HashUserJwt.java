package io.ajoss.hashchatserver.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class HashUserJwt {
	private final String SECURITY_KEY = "HashChat_User_Security_Key";

	public String exctractUserID(String token) {
		return exctractClaim(token, Claims::getSubject);
	}

	public Date exctractDate(String token) {
		return exctractClaim(token, Claims::getExpiration);
	}

	public <T> T exctractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = exctractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims exctractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
	}

	private boolean isTokenExpired(String token) {
		return exctractDate(token).before(new Date());
	}

	public String GenerateToken(UserDetails user) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, user.getUsername());
	}

	private String createToken(Map<String, Object> claims, String userID) {
		return Jwts.builder().addClaims(claims).setSubject(userID).setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS256, SECURITY_KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails user) {
		final String userID = exctractUserID(token);
		return userID.equals(user.getUsername());
	}

}
