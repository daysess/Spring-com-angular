package br.com.daysesoares.helpdesk.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO {

	private String id;
	private String email;
	private String password;
	private String profile;
	
	public UserDTO() {
	}

	public UserDTO(String id, String email, String password, String profile) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.profile = profile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

		
	
}
