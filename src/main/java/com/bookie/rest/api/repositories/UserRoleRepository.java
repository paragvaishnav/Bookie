package com.bookie.rest.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookie.rest.api.entity.Role;

@Repository
public interface UserRoleRepository extends JpaRepository<Role, Long> {
	Role findRoleByName(String name);
}

