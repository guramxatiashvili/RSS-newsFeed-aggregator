package com.guramikhatiashvili.feed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guramikhatiashvili.feed.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);

}
