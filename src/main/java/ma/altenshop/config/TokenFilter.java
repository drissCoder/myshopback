package ma.altenshop.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.altenshop.service.TokenService;

@Component
public class TokenFilter extends OncePerRequestFilter {

	
	private TokenService tokenService;
	private UserDetailsService userDetailsService;
	
	
	
	public TokenFilter(TokenService tokenService, UserDetailsService userDetailsService) {
		this.tokenService = tokenService;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		final String header = request.getHeader("Authorization");
		
		
		 if(header != null && header.startsWith("Bearer ") && tokenService.validateToken(header.substring(7))) {
			final String token = header.substring(7);
			final String email = tokenService.getUsername(token);
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			if(email != null && authentication == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(email);
				
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
							userDetails,
							null,
							userDetails.getAuthorities()
							);
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
		}
		filterChain.doFilter(request, response);
	
	}
}
