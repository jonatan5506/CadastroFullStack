package com.backend.cadastro.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.cadastro.models.Users;
import com.backend.cadastro.repositories.UserRepository;
import java.util.stream.Collectors;

@Service
public class UsersService {

	@Autowired
	private UserRepository repository;

	//ENCONTRAR USUARIO POR ID
	public Users findById(Long id) {
		Users user = repository.findById(id).get();
		return user;
	}

	//LISTAR USUÁRIOS CRIADOS NO BD
	public List<Users> usersList() {

		List<Users> listUsers = repository.findAll();
		List<Users> usersWithoutPassword = listUsers.stream()
				.map(user -> new Users(user.getId(), user.getNome(), user.getEmail(), user.getTel()))
				.collect(Collectors.toList());
		return usersWithoutPassword;
	}

	//CRIAR USUARIO
	public ResponseEntity<Users> createUser(Users user) {
		try {
			// Lógica para criar o usuário e salvar no banco de dados
			Users createdUser = repository.save(user);

			// Retorna o usuário criado com o código de status CREATED
			return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
		} catch (Exception e) {
			// Se ocorrer um erro ao criar o usuário, retorna um código de status
			// INTERNAL_SERVER_ERROR
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	//TODO ALTERAR USUARIO
}
