package com.backend.cadastro.dto;

import com.backend.cadastro.models.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String nome;

    public UserDto(){

    }

    public UserDto(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public UserDto(Users user){
        id = user.getId();
        nome = user.getNome();
    }
}
