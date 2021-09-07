package br.com.daysesoares.helpdesk.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
		String password = pe.encode(userDTO.getPassword());
		User user = new User();
		user.setId(userDTO.getId());
		user.setEmail(userDTO.getEmail());
		user.setPassword(password);
		user.addProfile(ProfileEnum.toDescription(userDTO.getProfile()));
		
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
		PageRequest pageRequest = PageRequest.of(page, count);
		return userRepository.findAll(pageRequest);
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
