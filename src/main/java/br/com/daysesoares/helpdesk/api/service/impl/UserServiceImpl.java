package br.com.daysesoares.helpdesk.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.daysesoares.helpdesk.api.dto.UserDTO;
import br.com.daysesoares.helpdesk.api.entity.User;
import br.com.daysesoares.helpdesk.api.enums.ProfileEnum;
import br.com.daysesoares.helpdesk.api.repository.UserRepository;
import br.com.daysesoares.helpdesk.api.security.UserSS;
import br.com.daysesoares.helpdesk.api.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User createOrUpdate(UserDTO userDTO) {
		String senha = pe.encode(userDTO.getSenha());
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setPassword(senha);
		user.addProfile(ProfileEnum.ROLE_ADMIN);
		
		return userRepository.save(user);
	}

	@Override
	public User findById(String id) {
		return userRepository.findById(id).get();
	}

	@Override
	public void delete(String id) {
		User user = userRepository.findById(id).get();
		if(user != null) {
			userRepository.delete(user);
		}
				
	}

	@Override
	public Page<User> findAll(int page, int count) {
		@SuppressWarnings("deprecation")
		Pageable pages = new QPageRequest(page, count);
		return userRepository.findAll(pages);
	}

	@Override
	public UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch(Exception e) {
			return null;
		}
	}

}
