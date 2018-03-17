package com.abex.arena.core.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abex.arena.core.persistence.model.user.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);
   
}
