package br.com.daysesoares.helpdesk.api.enums;

public enum ProfileEnum {

	ROLE_ADMIN(1, "ROLE_ADMIN"),
	ROLE_CUSTOMER(2, "ROLE_CUSTOMER"),
	ROLE_TECHNICIAN(3, "ROLE_TECHNICIAN");
	
	private Integer cod;
	private String description;
	
	
	private ProfileEnum(Integer cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

		throw new IllegalArgumentException("Id invalid: " + cod);
	}



	public static ProfileEnum toDescription(String profile) {
		if (profile == null) {
			return null;
		}
		for(ProfileEnum x : ProfileEnum.values()) {
			if(profile.equals(x.getDescription())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid Profile Description: " + profile);
	}
	
}
