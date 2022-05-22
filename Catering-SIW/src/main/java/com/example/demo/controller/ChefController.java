package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.controller.validator.ChefValidator;
import com.example.demo.service.ChefService;

@Controller
public class ChefController {

	@Autowired
	ChefService chefService;
	
	@Autowired
	ChefValidator chefValidator;
}
