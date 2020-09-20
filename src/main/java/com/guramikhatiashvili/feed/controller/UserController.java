package com.guramikhatiashvili.feed.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.guramikhatiashvili.feed.entity.Blog;
import com.guramikhatiashvili.feed.service.BlogService;
import com.guramikhatiashvili.feed.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BlogService blogService;

	@ModelAttribute("blog")
	public Blog constructBlog() {
		return new Blog();
	}
    /**
     * Shows myaccount and user's blog
     * @param model user with his/hers blog
     * @param principal authenticated user
     * @return returns myaccount with user data
     */
	@RequestMapping("/account")
	public String account(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithBlogs(name));
		return "account";
	}
    /**
     * Save blog into users account page
     * @param model user data
     * @param blog blog data
     * @param result result of adding blog(success or error)
     * @param principal authenticated user
     * @return returns saved blogs and redirects to account page 
     */
	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String doAddBlog(Model model,
			@Valid @ModelAttribute("blog") Blog blog, BindingResult result,
			Principal principal) {
		if (result.hasErrors()) {
			return account(model, principal);
		}
		String name = principal.getName();
		blogService.save(blog, name);
		return "redirect:/account.html";
	}
    /**
     * Delete blog from user account
     * @param id user id 
     * @return  remaining blogs
     */
	@RequestMapping("/blog/remove/{id}")
	public String removeBlog(@PathVariable int id) {
		Blog blog = blogService.findOne(id);
		blogService.delete(blog);
		return "redirect:/account.html";
	}

}
