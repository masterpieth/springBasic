package com.basic.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.basic.basic.service.UserService;

@Controller
public class BoardController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/intro/board", method = RequestMethod.GET)
	public String board(Model model) {
		return "/intro/board";
	}
}
