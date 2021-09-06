package br.com.daysesoares.helpdesk.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.daysesoares.helpdesk.api.dto.UserDTO;
import br.com.daysesoares.helpdesk.api.entity.User;
import br.com.daysesoares.helpdesk.api.response.Response;
import br.com.daysesoares.helpdesk.api.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> create(@Valid @RequestBody UserDTO userDTO){
		User obj = userService.createOrUpdate(userDTO);
		if(obj == null) {
			return ResponseEntity.badRequest().body("Erro ao cadastrar novo usuario!");
		}
		userDTO.setSenha(null);
		return ResponseEntity.ok(userDTO);
	}

	@PutMapping 
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<User>> update(HttpServletRequest request, 
			@RequestBody UserDTO userDTO, BindingResult result) {
		Response<User> response = new Response<User>();
		try{
			validateUpdateUser(userDTO, result);
			if(result.hasErrors()){
				result.getAllErrors().forEach(error -> response.getErrors().add(
						error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			User userPersisted = userService.createOrUpdate(userDTO);
			response.setData(userPersisted);
		} catch(Exception e){
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}


	private void validateUpdateUser(UserDTO userDTO, BindingResult result){
		if(userDTO.getEmail() == null){
			result.addError(new ObjectError("User","Email no information"));
		}else {
			User user = userService.findByEmail(userDTO.getEmail());
			if(user == null) {
				result.addError(new ObjectError("User","Email no information"));
			}
		}
		
	}

	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<User>> findById(@PathVariable("id") String id) {
		Response<User> response = new Response<User>();
		User user = userService.findById(id);
		if(user == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(user);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> deleteById(@PathVariable("id") String id) {
		Response<String> response = new Response<String>();
		User user = userService.findById(id);
		if(user == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		userService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<User>>> findAll(@PathVariable int page, @PathVariable int count){
		Response<Page<User>> response = new Response<Page<User>>();
		Page<User> users = userService.findAll(page, count);
		response.setData(users);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/teste")
	public ResponseEntity<?> teste(){
		return ResponseEntity.ok("teste");
	}
	
}
