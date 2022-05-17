package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.controller.validator.PiattoValidator;
import it.uniroma3.siw.service.PiattoService;

@Controller
public class PiattoController {

	@Autowired
	PiattoService piattoService;
	
	@Autowired
	PiattoValidator piattoValidator;
}
