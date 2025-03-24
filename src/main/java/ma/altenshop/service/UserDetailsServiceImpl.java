package ma.altenshop.service;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.altenshop.entities.User;
import ma.altenshop.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email).orElseThrow(() -> 
										new UsernameNotFoundException("User not found"));
		
		Set<GrantedAuthority> authorities = email.equals("admin@admin.com") ? Set.of(new SimpleGrantedAuthority("ADMIN")) : Set.of();
		
		
		return new org.springframework.security.core.userdetails
				.User(user.getEmail(), user.getPassword(), authorities);
	}

}
