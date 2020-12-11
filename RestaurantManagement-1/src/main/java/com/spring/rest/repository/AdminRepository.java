package com.spring.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{

	public Admin findByUsernameAndPassword(String username,String password);
}
