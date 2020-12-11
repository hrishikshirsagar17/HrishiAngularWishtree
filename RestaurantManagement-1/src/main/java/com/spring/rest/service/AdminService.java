package com.spring.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.entity.Admin;
import com.spring.rest.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository repository;
	
	public AdminService() {
		// TODO Auto-generated constructor stub
	}
	public AdminService(AdminRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Admin fetchAdminByUsernameAndPassword(String username,String password)
	{
		return repository.findByUsernameAndPassword(username, password);
	}
	public Admin registerAdmin(Admin admin)
	{
		return repository.save(admin);
	}
}
