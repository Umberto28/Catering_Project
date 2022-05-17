package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.controller.validator.IngredienteValidator;
import it.uniroma3.siw.service.IngredienteService;

@Controller
public class IngredienteController {

	@Autowired
	IngredienteService ingredienteService;
	
	@Autowired
	IngredienteValidator ingredienteValidator;
}
