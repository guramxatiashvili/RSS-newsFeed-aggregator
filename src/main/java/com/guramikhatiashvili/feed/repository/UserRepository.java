package com.guramikhatiashvili.feed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guramikhatiashvili.feed.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByName(String name);


}
