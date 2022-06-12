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

import com.example.demo.controller.validator.BuffetValidator;
import com.example.demo.model.Buffet;
import com.example.demo.service.BuffetService;

@Controller
public class BuffetController {

	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private BuffetValidator buffetValidator;
	
	@PostMapping("/buffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model) {
		this.buffetValidator.validate(buffet, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.buffetService.inserisci(buffet);
			model.addAttribute("buffet", this.buffetService.findById(buffet.getId()));
			return "buffet.html";
		}
		else {
			return "buffetForm.html";
		}
	}
	
	@PostMapping("/buffetUpdate")
	public String updateBuffetForm(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model) {
		this.buffetValidator.validate(buffet, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.buffetService.inserisci(buffet);
			model.addAttribute("buffet", this.buffetService.findById(buffet.getId()));
			return "buffet.html";
		}
		else {
			return "buffetUpdateForm.html";
		}
	}
	
	@GetMapping("/elencoBuffet")
	public String getElencoBuffet(Model model) {
		List<Buffet> elencoBuffet = this.buffetService.findAll();
		model.addAttribute("elencoBuffet", elencoBuffet);
		return "elencoBuffet.html";
	}
	
	@GetMapping("/buffet/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		model.addAttribute("elencoPiattiBuffet", buffet.getListaPiatti());
		model.addAttribute("chef", buffet.getChefDelBuffet());
		return "buffet.html";
	}
	
	@GetMapping("/buffetForm")
	public String getBuffetForm(Model model) {
		model.addAttribute("buffet", new Buffet());
		return "buffetForm.html";
	}
	
	@GetMapping("/deleteBuffet")
	public String deleteBuffet(@RequestParam Long buffetId) {
		this.buffetService.rimuovi(buffetId);
		return "redirect:/elencoBuffet";
	}
	
	@GetMapping("/updateBuffet")
	public String updateBuffet(@RequestParam Long buffetId, Model model) {
		System.out.println("L'id del buffet: " + buffetId);
		model.addAttribute("buffet", this.buffetService.findById(buffetId));
		return "buffetUpdateForm.html";
	}
}
