package com.backend.cadastro.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.cadastro.dto.UserDto;
import com.backend.cadastro.models.Users;
import com.backend.cadastro.repositories.UserRepository;

import java.util.stream.Collectors;

@Service
public class UsersService {

	@Autowired
	private UserRepository repository;

	//Chamamos para criptografar a senha do usuário
	//foi necessário adicionar a dependência security ao pom
	private PasswordEncoder encoder;

	public UsersService(){
		this.encoder = new BCryptPasswordEncoder();
	}


	//Encontrar usuário por Id
	public UserDto findById(Long id){
		Users entity = repository.findById(id).get();//findById retorna um object OPTIONAL. quando aciono o .get() eu aciono o objeto dentro o optional
		UserDto dto = new UserDto(entity);
		return dto;
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
		String encoder = this.encoder.encode(user.getSenha());
		user.setSenha(encoder);
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

	//Não funciona ainda, melhorar
	public Boolean validarAcesso(Users user) {
		String senha = repository.getReferenceById(user.getId()).getSenha();
		Boolean valid = encoder.matches(user.getSenha(),senha);
		return valid;
	}
	//TODO ALTERAR USUARIO
}
