package br.com.daysesoares.helpdesk.api.enums;

public enum ProfileEnum {

	ROLE_ADMIN(1, "ROLE_ADMIN"),
	ROLE_COSTUMER(2, "ROLE_COSTUMER"),
	ROLE_TECHNICIAN(3, "ROLE_TECHNICIAN");
	
	private Integer cod;
	private String descricao;
	
	
	private ProfileEnum(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}



	public Integer getCod() {
		return cod;
	}



	public void setCod(Integer cod) {
		this.cod = cod;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public static ProfileEnum toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (ProfileEnum x : ProfileEnum.values()) {
			if (cod == x.getCod()) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
