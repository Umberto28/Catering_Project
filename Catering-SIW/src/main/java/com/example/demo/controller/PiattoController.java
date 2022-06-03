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

import com.example.demo.controller.validator.PiattoValidator;
import com.example.demo.model.Piatto;
import com.example.demo.service.PiattoService;

@Controller
public class PiattoController {

	@Autowired
	PiattoService piattoService;
	
	@Autowired
	PiattoValidator piattoValidator;
	
	@PostMapping("/piatto")
	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, Model model) {
		this.piattoValidator.validate(piatto, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.piattoService.inserisci(piatto);
			model.addAttribute("piatto", this.piattoService.findById(piatto.getId()));
			return "piatto.html";
		}
		else {
			return "piattoForm.html";
		}
	}
	
	@PostMapping("/piattoUpdate")
	public String updatePiattoForm(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, Model model) {
		this.piattoValidator.validate(piatto, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.piattoService.inserisci(piatto);
			model.addAttribute("piatto", this.piattoService.findById(piatto.getId()));
			return "piatto.html";
		}
		else {
			return "piattoUpdateForm.html";
		}
	}
	
	@GetMapping("/elencoPiatti")
	public String getElencoPiatti(Model model) {
		List<Piatto> elencoPiatti = this.piattoService.findAll();
		model.addAttribute("elencoPiatti", elencoPiatti);
		return "elencoPiatti.html";
	}
	
	@GetMapping("/piatto/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model) {
		Piatto piatto = this.piattoService.findById(id);
		model.addAttribute("piatto", piatto);
		model.addAttribute("elencoIngredientiPiatto", piatto.getIngredientiDelPiatto());
		return "piatto.html";
	}
	
	@GetMapping("/piattoForm")
	public String getPiattoForm(Model model) {
		model.addAttribute("piatto", new Piatto());
		return "piattoForm.html";
	}
	
	@GetMapping("/deletePiatto")
	public String deletePiatto(@RequestParam Long piattoId) {
		this.piattoService.rimuovi(piattoId);
		return "redirect:/elencoPiatti";
	}
	
	@GetMapping("/updatePiatto")
	public String updatePiatto(@RequestParam Long piattoId, Model model) {
		System.out.println("L'id del piatto: " + piattoId);
		model.addAttribute("piatto", this.piattoService.findById(piattoId));
		return "piattoUpdateForm.html";
	}
}
