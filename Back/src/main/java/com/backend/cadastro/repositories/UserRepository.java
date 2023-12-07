package com.backend.cadastro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.cadastro.models.Users;

public interface UserRepository extends JpaRepository<Users,Long>{
	
	Users findByEmail(String email);
}
