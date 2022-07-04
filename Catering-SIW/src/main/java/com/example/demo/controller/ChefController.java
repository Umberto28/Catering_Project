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

import com.example.demo.controller.validator.ChefValidator;
import com.example.demo.model.Chef;
import com.example.demo.service.BuffetService;
import com.example.demo.service.ChefService;

@Controller
public class ChefController {

	@Autowired
	ChefService chefService;
	@Autowired
	BuffetService buffetService;
	
	@Autowired
	ChefValidator chefValidator;
	
	@PostMapping("/admin/chef")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {
		this.chefValidator.validate(chef, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.chefService.inserisci(chef);

			model.addAttribute("elencoChef", this.chefService.findAll());
			return "elencoChef.html";
		}
		else {
			return "chefForm.html";
		}
	}
	
	@PostMapping("/admin/chefUpdate/{id}")
	public String updateChefForm(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {
		this.chefValidator.validate(chef, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.chefService.inserisci(chef);
			model.addAttribute("chef", chef);
			model.addAttribute("buffetDelloChef", chef.getBuffetDelloChef());
			return "chef.html";
		}
		else {
			return "chefUpdateForm.html";
		}
	}
	
	@GetMapping("/elencoChef")
	public String getElencoChef(Model model) {
		List<Chef> elencoChef = this.chefService.findAll();
		model.addAttribute("elencoChef", elencoChef);
		return "elencoChef.html";
	}
	
	@GetMapping("/chef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model) {
		Chef chef = this.chefService.findById(id);
		model.addAttribute("chef", chef);
		model.addAttribute("buffetDelloChef", chef.getBuffetDelloChef());
		return "chef.html";
	}
	
	@GetMapping("/admin/chefForm")
	public String getChefForm(Model model) {
		model.addAttribute("chef", new Chef());
		return "chefForm.html";
	}
	
	@GetMapping("/admin/deleteChef")
	public String deleteChef(@RequestParam Long chefId) {
		this.chefService.rimuovi(chefId);
		return "redirect:/elencoChef";
	}
	
	@GetMapping("/admin/updateChef")
	public String updateChef(@RequestParam Long chefId, Model model) {
		model.addAttribute("chef", this.chefService.findById(chefId));
		return "chefUpdateForm.html";
	}
}
