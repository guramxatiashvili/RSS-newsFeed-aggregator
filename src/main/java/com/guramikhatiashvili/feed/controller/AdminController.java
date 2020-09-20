package com.guramikhatiashvili.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guramikhatiashvili.feed.service.UserService;
/**
 * This class is admin controller
 * @author guga_x
 * @version 1.0
 */
@Controller
@RequestMapping("/users")
public class AdminController {

	@Autowired
	private UserService userService;
	/**
	 * lists all users
	 * @param model all users
	 * @return  list of users
	 */
	@RequestMapping
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}
	/**
     * lists user with his/hers blogs
     * @param model user and blogs
     * @param id user id
     * @return user details and blogs
     */
	@RequestMapping("/{id}")
	public String detail(Model model, @PathVariable int id) {
		model.addAttribute("user", userService.findOneWithBlogs(id));
		return "user-detail";
	}
	/**
     * Delete user  
     * @param id user id 
     * @return redirects to remaining users
     */
	@RequestMapping("/remove/{id}")
	public String removeUser(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/users.html";
	}

}
