package ma.altenshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	private UserDetailsService userDetailsService;
	
	private TokenFilter tokenFilter;
	
	public SecurityConfig(UserDetailsService userDetailsService, TokenFilter tokenFilter) {
		this.userDetailsService = userDetailsService;
		this.tokenFilter = tokenFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		.cors(Customizer.withDefaults())
		.csrf(csrf -> csrf.disable())
		.headers(hdrs -> hdrs.frameOptions(fr -> fr.disable()))
		.authorizeHttpRequests(auth ->
				auth.requestMatchers("/auth/**", "/h2-console/**").permitAll()
				)
		.authorizeHttpRequests(auth ->
		auth.requestMatchers("/api/**").hasAuthority("ADMIN")
		)
		.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				)
			.sessionManagement(session ->
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider;
	}
}
