package br.com.daysesoares.helpdesk.api.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.daysesoares.helpdesk.api.enums.ProfileEnum;


@Document
public class User {

	@Id	
	private String id;
	
	@Indexed(unique = true)
	@NotBlank(message = "email requerid")
	@Email(message = "email invalid")
	private String email;
	
	@NotBlank(message = "password requerid")
	@Size(min = 6)
	private String password;
		
	private Set<Integer> profiles = new HashSet<>();

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
		
	public Set<ProfileEnum> getProfiles(){
		return profiles.stream().map(x -> ProfileEnum.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addProfile(ProfileEnum profile) {
		profiles.add(profile.getCod());
	}
	
	
	
}
