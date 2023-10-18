package com.group1.dev.app.Jwt;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final int EXPIRATION_TIME_IN_MIN = 60;

	private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String generateJWT(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MIN * 60 * 1000))
				.signWith(secretKey, SignatureAlgorithm.HS256).compact();
	}

	private Claims extractAllClaims(String token) {

		return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private String extractUsername(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getSubject);
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public boolean validateToken(String token, UserDetails userDetails) {

		String username = extractUsername(token);

		return (username.equals(userDetails.getUsername()) && isTokenNotExpired(token));

	}

	private boolean isTokenNotExpired(String token) {

		return extractExpiration(token).before(new Date());
	}

}
