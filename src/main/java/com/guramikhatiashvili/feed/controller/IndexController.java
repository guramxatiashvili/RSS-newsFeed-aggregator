package com.guramikhatiashvili.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guramikhatiashvili.feed.service.ItemService;

@Controller
public class IndexController {
	
	@Autowired
	private ItemService itemService;
	/**
	 * getting and listing items from
	 * blogs into homepage
	 * @param model feed data
	 * @return homepage
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("items", itemService.getItems());
		return "index";
	}
}
