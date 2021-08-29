package br.com.daysesoares.helpdesk.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import br.com.daysesoares.helpdesk.api.entity.User;
import br.com.daysesoares.helpdesk.api.repository.UserRepository;
import br.com.daysesoares.helpdesk.api.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User createOrUpdate(User user) {
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

}
