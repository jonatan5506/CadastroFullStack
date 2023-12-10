package com.backend.cadastro.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.cadastro.dto.UserDto;
import com.backend.cadastro.models.Users;
import com.backend.cadastro.services.UsersService;

@RestController
@CrossOrigin("*")//IMPORTANTE!!! //Libera qualquer entrada na API
@RequestMapping(value = "/api")
public class Controller {

	@Autowired
	private UsersService service;
	
	@GetMapping(value = "/{id}")
	@Transactional(readOnly = true)// Informa que a operação não precisa ser salva no BD, isto torna  a operação mais rápida. Se não fosse apenas leitura, deixaria só o Transactional
	public UserDto findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@GetMapping(value = "/list")
	public ResponseEntity<List<Users>> listUsers(){
		
		//List<Users> list = service.usersList();
		return ResponseEntity.status(200).body(service.usersList());
	}
	
	@PostMapping
	public ResponseEntity<Users> createUser(@RequestBody Users user ) {
        return service.createUser(user);
    }


	//Não Funcionou ainda
	@PostMapping("/login")
	public ResponseEntity<Users> validarAcesso(@RequestBody Users user){
		Boolean valid = service.validarAcesso(user);
		if(!valid) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return 	ResponseEntity.status(200).build();
	}
}
