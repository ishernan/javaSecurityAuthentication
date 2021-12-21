package com.aelion.mycrm.components;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	@Value("${jwt.secret}")//read value inside application properties
	private String jwtSecret;
	
	@Value("${jwt.token.validity}")
	private long tokenValidity; 
	
	public String getUserName(final String token) {		
		try {
			Claims body = Jwts
					.parserBuilder()//je recupere un token 
					.setSigningKey(this.jwtSecret)// je passe la cle
					.build()
					.parseClaimsJws(token)//on peut decoder 
					.getBody(); //body contient date de expiration, le truc encodé
			
			return body.getSubject();
		} catch(Exception e) {
			System.out.println(e.getMessage() + " => " + e);
		}
		
		return null; 
		
	}
	
	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		
		Claims claims = Jwts.claims().setSubject(user.getUsername());
		
		final long now = System.currentTimeMillis();
		final long expire = now + this.tokenValidity; 
		
		Date expirationDate = new Date(expire); 
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(now))
				.setExpiration(expirationDate)
				.signWith(this.getSigninKey(), SignatureAlgorithm.HS256).compact();
	}
	
	private Key getSigninKey() {
		byte[] keyBytes = Decoders.BASE64.decode(this.jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
//	public void validateToken(final String token) {
//		try {
//			Jwts.parserBuilder().setSigningKey(this.jwtSecret).build().parse(token)
//		}catch ()
//	}
	
	
	
	
	
	
	
	
	
}
