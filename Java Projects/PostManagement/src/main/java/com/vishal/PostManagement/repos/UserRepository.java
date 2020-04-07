package com.vishal.PostManagement.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.PostManagement.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	List<User>findByusertypeid(int usertypeid);
}
