package com.guramikhatiashvili.feed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guramikhatiashvili.feed.entity.Blog;
import com.guramikhatiashvili.feed.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer>{

	List<Blog> findByUser(User user);
}
