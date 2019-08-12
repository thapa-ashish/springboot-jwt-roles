package com.restfulabs.cas.config.security.jwt;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTProvider {

	@Value("${cas.jwt.secret}")
	private String jwtSecret;

	@Value("${cas.jwt.expiration}")
	private int jwtExpiration;

	public String generateJWTToken(Authentication authentication) {

		Principal userPrincipal = (Principal) authentication.getPrincipal();

		return Jwts.builder().setSubject(userPrincipal.getName()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
	}

	public boolean validateJwtToken(String token) {
		boolean valid = false;
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			valid = true;
		} catch (Exception e) {

		}
		return valid;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
}
