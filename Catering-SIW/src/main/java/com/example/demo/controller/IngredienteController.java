package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.validator.IngredienteValidator;
import com.example.demo.model.Ingrediente;
import com.example.demo.service.IngredienteService;

@Controller
public class IngredienteController {

	@Autowired
	IngredienteService ingredienteService;
	
	@Autowired
	IngredienteValidator ingredienteValidator;
	
	@PostMapping("/ingrediente")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.ingredienteService.inserisci(ingrediente);
			model.addAttribute("ingrediente", this.ingredienteService.findById(ingrediente.getId()));
			return "ingrediente.html";
		}
		else {
			return "ingredienteForm.html";
		}
	}
	
	@PostMapping("/ingredienteUpdate")
	public String updateIngredienteForm(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.ingredienteService.inserisci(ingrediente);
			model.addAttribute("ingrediente", this.ingredienteService.findById(ingrediente.getId()));
			return "ingrediente.html";
		}
		else {
			return "ingredienteUpdateForm.html";
		}
	}
	
	@GetMapping("/elencoIngredienti")
	public String getElencoIngrediente(Model model) {
		List<Ingrediente> elencoIngredienti = this.ingredienteService.findAll();
		model.addAttribute("elencoIngredienti", elencoIngredienti);
		return "elencoIngredienti.html";
	}
	
	@GetMapping("/ingrediente/{id}")
	public String getIngrediente(@PathVariable("id") Long id, Model model) {
		Ingrediente ingrediente = this.ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "ingrediente.html";
	}
	
	@GetMapping("/ingredienteForm")
	public String getIngredienteForm(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "ingredienteForm.html";
	}
	
	@GetMapping("/deleteIngrediente")
	public String deleteIngrediente(@RequestParam Long ingredienteId) {
		this.ingredienteService.rimuovi(ingredienteId);
		return "redirect:/elencoIngredienti";
	}
	
	@GetMapping("/updateIngrediente")
	public String updateIngrediente(@RequestParam Long ingredienteId, Model model) {
		System.out.println("L'id dell'ingrediente: " + ingredienteId);
		model.addAttribute("ingrediente", this.ingredienteService.findById(ingredienteId));
		return "ingredienteUpdateForm.html";
	}
}
