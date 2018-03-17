package com.abex.arena.core.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abex.arena.core.persistence.model.user.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	UserRole findByName(String name);

	@Override
	void delete(UserRole user);

}
