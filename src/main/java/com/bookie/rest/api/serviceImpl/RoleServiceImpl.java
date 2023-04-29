package com.bookie.rest.api.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookie.rest.api.entity.Role;
import com.bookie.rest.api.repositories.UserRoleRepository;
import com.bookie.rest.api.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private UserRoleRepository roleRepo;
	
	@Override
	public Role findByName(String name) {
		Role role = roleRepo.findRoleByName(name);
        return role;
	}
}
