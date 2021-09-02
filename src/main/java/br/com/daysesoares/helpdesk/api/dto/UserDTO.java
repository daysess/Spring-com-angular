package br.com.daysesoares.helpdesk.api.dto;

public class UserDTO {

	private String email;
	private String senha;
	
	public UserDTO() {
	}
	
	public UserDTO(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
