package ma.altenshop.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.altenshop.dto.AccountRequest;
import ma.altenshop.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

	
	private UserService userService;
	
	
	public UserController(UserService userService) {
		this.userService = userService;
	}


	@PostMapping("/account")
	public ResponseEntity<?> createAccount(@RequestBody AccountRequest accountRequest){
		
		try {
			return ResponseEntity.ok(userService.createAccount(accountRequest));
		}catch(Exception e) {
			ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
			return ResponseEntity.of(problemDetail).build();
		}
	}
	
	@PostMapping("/token")
	public ResponseEntity<?> loginAndToken(@RequestBody AccountRequest accountRequest) {
		
		try {
			return ResponseEntity.ok(userService.loginAndToken(accountRequest));
		}catch(Exception e) {
			ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
			return ResponseEntity.of(problemDetail).build();
		}
	}
}
