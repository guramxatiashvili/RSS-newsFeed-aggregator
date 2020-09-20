package com.guramikhatiashvili.feed.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.guramikhatiashvili.feed.entity.Blog;
import com.guramikhatiashvili.feed.entity.Item;
import com.guramikhatiashvili.feed.entity.Role;
import com.guramikhatiashvili.feed.entity.User;
import com.guramikhatiashvili.feed.repository.BlogRepository;
import com.guramikhatiashvili.feed.repository.ItemRepository;
import com.guramikhatiashvili.feed.repository.RoleRepository;
import com.guramikhatiashvili.feed.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	public void init() {
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			Role roleUser = new Role();
			roleUser.setName("ROLE_USER");
			roleRepository.save(roleUser);

			Role roleAdmin = new Role();
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);

			User userAdmin = new User();
			userAdmin.setEnabled(true);
			userAdmin.setName("admin");
			// password encryption
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			userAdmin.setPassword(encoder.encode("admin"));
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleAdmin);
			roles.add(roleUser);
			userAdmin.setRoles(roles);
			userRepository.save(userAdmin);

			Blog bbcnews = new Blog();
			bbcnews.setName("BBCnews");
			bbcnews
					.setUrl("http://feeds.bbci.co.uk/news/world/rss.xml");
			bbcnews.setUser(userAdmin);
			blogRepository.save(bbcnews);

		}

	}
}
