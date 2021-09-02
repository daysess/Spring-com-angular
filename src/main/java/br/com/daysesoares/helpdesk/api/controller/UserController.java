package br.com.daysesoares.helpdesk.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.daysesoares.helpdesk.api.dto.UserDTO;
import br.com.daysesoares.helpdesk.api.entity.User;
import br.com.daysesoares.helpdesk.api.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/insert")
	public ResponseEntity<?> insert(@Valid @RequestBody UserDTO userDTO){
		User obj = userService.createOrUpdate(userDTO);
		userDTO.setSenha(null);
		return ResponseEntity.ok(userDTO);
	}
	
	@GetMapping(value = "/teste")
	public ResponseEntity<?> teste(){
		return ResponseEntity.ok("teste");
	}
	
}
