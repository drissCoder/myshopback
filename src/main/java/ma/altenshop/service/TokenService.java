package ma.altenshop.service;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenService {

	
	private String secret = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b";
	
	private long dateExperiation = 3600000L;
	
	private Date expirationDate = null;
	
	public String generateToken(Authentication authentication) {
		
		String username = authentication.getName();
		
		Date currentDate = new Date();
		
		expirationDate = new Date(currentDate.getTime() + dateExperiation);
		
		String token = Jwts.builder()
						.setSubject(username)
						.setIssuedAt(new Date())
						.setExpiration(expirationDate)
						.signWith(getSignInKey(), SignatureAlgorithm.HS256)
						.compact();
		
		return token;
	}
	
	private Key getSignInKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}
	
	public Claims extractClaims(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
				
	}
	
	public String getUsername(String token) {
		
		
	Claims claims = extractClaims(token);
	return claims.getSubject();
				
	}
	
	public long getDateExperiation() {
		
		

		return expirationDate.getTime();
					
		}
	
	public boolean validateToken(String token) {
		
		try {
		 	Jwts.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parse(token);
		 	return true;
		} catch (MalformedJwtException e) {
            System.err.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
        	System.err.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
        	System.err.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
        	System.err.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
		} 
}
