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
import com.example.demo.model.Chef;
import com.example.demo.service.BuffetService;
import com.example.demo.service.ChefService;

@Controller
public class BuffetController {

	@Autowired
	private BuffetService buffetService;
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private BuffetValidator buffetValidator;
	
	@PostMapping("/admin/buffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, 
			@RequestParam(name = "chefScelto") Long id, 
			BindingResult bindingResult, Model model) {
		
		this.buffetValidator.valC(id, bindingResult);
		this.buffetValidator.validate(buffet, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			
			Chef chef = this.chefService.findById(id);
			
			buffet.setChefDelBuffet(chef);
			
			chef.getBuffetDelloChef().add(buffet);
			
			this.chefService.inserisci(chef);
			
			model.addAttribute("buffet", buffet);
			model.addAttribute("listaPiatti", buffet.getListaPiatti());
			model.addAttribute("chef", buffet.getChefDelBuffet());
			return "buffet.html";
		}
		model.addAttribute("buffet", buffet);
		model.addAttribute("chefDisponibili", this.chefService.findAll());
		return "buffetForm.html";
	}
	
	@PostMapping("/admin/buffetUpdate/{id}")
	public String updateBuffetForm(@Valid @ModelAttribute("buffet") Buffet buffet,
			@RequestParam(name = "chefScelto") Long id,
			BindingResult bindingResult, Model model) {
		
		this.buffetValidator.validate(buffet, bindingResult);
		if(!bindingResult.hasErrors()) {
			Chef CNuovo = this.chefService.findById(id);
			Chef CVecchio = buffet.getChefDelBuffet();
			
			if(CVecchio != null) {
				for(Buffet bInList : CVecchio.getBuffetDelloChef()) {
					if(bInList.getId() == buffet.getId()) {
						CVecchio.getBuffetDelloChef().remove(bInList);
					}
				}
			}
			
			buffet.setChefDelBuffet(CNuovo);
			CNuovo.getBuffetDelloChef().add(buffet);
			
			this.chefService.inserisci(CNuovo);
			
			model.addAttribute("buffet", buffet);
			model.addAttribute("listaPiatti", buffet.getListaPiatti());
			model.addAttribute("chef", buffet.getChefDelBuffet());
			return "buffet.html";
		}
		model.addAttribute("buffet", buffet);
		model.addAttribute("chefDisponibili", this.chefService.findAll());
		return "buffetUpdateForm.html";
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
		model.addAttribute("listaPiatti", buffet.getListaPiatti());
		model.addAttribute("chef", buffet.getChefDelBuffet());
		return "buffet.html";
	}
	
	@GetMapping("/admin/buffetForm")
	public String getBuffetForm(Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chefDisponibili", this.chefService.findAll());
		return "buffetForm.html";
	}
	
	@GetMapping("/admin/deleteBuffet")
	public String deleteBuffet(@RequestParam Long buffetId) {
		this.buffetService.rimuovi(buffetId);
		return "redirect:/elencoBuffet";
	}
	
	@GetMapping("/admin/updateBuffet")
	public String updateBuffet(@RequestParam Long buffetId, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(buffetId));
		model.addAttribute("chefDisponibili", this.chefService.findAll());
		return "buffetUpdateForm.html";
	}
}
