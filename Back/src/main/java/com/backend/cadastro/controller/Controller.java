package com.backend.cadastro.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.cadastro.models.Users;
import com.backend.cadastro.services.UsersService;

@RestController
@CrossOrigin("*")//IMPORTANTE!!! //Libera qualquer entrada na API
@RequestMapping(value = "/api")
public class Controller {

	@Autowired
	private UsersService service;
	
	@GetMapping(value = "/{id}")
	public Users findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@GetMapping(value = "/list")
	public List<Users> listUsers(){
		return service.usersList();
	}
	
	@PostMapping
	public ResponseEntity<Users> createUser(@RequestBody Users user ) {
        return service.createUser(user);
    }
}
