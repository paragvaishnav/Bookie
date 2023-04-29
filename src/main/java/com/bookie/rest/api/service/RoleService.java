package com.bookie.rest.api.service;

import com.bookie.rest.api.entity.Role;

public interface RoleService {
	public Role findByName(String name);
}
