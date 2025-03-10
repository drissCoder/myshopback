package com.altenshop.ecom.dtos;

public class RegisterUserDto {
	
	private String username;
	private String firstname;
    private String email;
    private String password;
    

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
        return email;
    }

    public RegisterUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

	@Override
	public String toString() {
		return "RegisterUserDto [username=" + username + ", fristname=" + firstname + ", email=" + email + ", password="
				+ password + "]";
	}
}
