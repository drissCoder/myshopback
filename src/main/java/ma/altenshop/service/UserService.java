package ma.altenshop.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import ma.altenshop.dto.AccountRequest;
import ma.altenshop.dto.TokenResponse;
import ma.altenshop.entities.User;
import ma.altenshop.repository.UserRepository;

@Service
public class UserService {
	
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private TokenService tokenService;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, TokenService tokenService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}
	
	
	public User createAccount(AccountRequest accountRequest) {
		
		if(accountRequest.getFirstname() == null || accountRequest.getFirstname() == null
				|| accountRequest.getFirstname() == null || accountRequest.getFirstname() == null) {
			throw new RuntimeException("Field cannot be null");
		}
		User user = new User();
		user.setEmail(accountRequest.getEmail());
		user.setFirstname(accountRequest.getFirstname());
		user.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
		user.setUsername(accountRequest.getUsername());
		
		return userRepository.save(user);
	}
	
	
	public TokenResponse loginAndToken(@RequestBody AccountRequest accountRequest) throws Exception {
		
			Authentication authentication = authenticate(accountRequest.getEmail(), accountRequest.getPassword());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			TokenResponse tokenResponse = new TokenResponse();
			tokenResponse.setAccessToken(tokenService.generateToken(authentication));
			tokenResponse.setDateExpiration(tokenService.getDateExperiation());
			
			return tokenResponse;
	}
	
	private Authentication authenticate(String username, String password) throws Exception {
		
		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			throw new Exception("Bad Credentials", e);
		}
	}	
	

}
