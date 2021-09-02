package br.com.daysesoares.helpdesk.api.service;

import org.springframework.data.domain.Page;

import br.com.daysesoares.helpdesk.api.dto.UserDTO;
import br.com.daysesoares.helpdesk.api.entity.User;
import br.com.daysesoares.helpdesk.api.security.UserSS;

public interface UserService {

	User findByEmail(String email);
	
	User createOrUpdate(UserDTO userDTO);
	
	User findById(String id);
	
	void delete(String id);
	
	Page<User> findAll(int page, int count);
	
	UserSS authenticated();
	
}
