package ma.altenshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.altenshop.entities.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
	
}
