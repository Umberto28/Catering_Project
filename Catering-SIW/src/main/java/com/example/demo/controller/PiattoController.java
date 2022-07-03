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
import com.example.demo.model.Buffet;
import com.example.demo.model.Piatto;
import com.example.demo.service.BuffetService;
import com.example.demo.service.IngredienteService;
import com.example.demo.service.PiattoService;

@Controller
public class PiattoController {

	@Autowired
	PiattoService piattoService;
	@Autowired
	BuffetService buffetService;
	@Autowired
	IngredienteService ingredienteService;
	
	@Autowired
	PiattoValidator piattoValidator;
	
	@PostMapping("/admin/piatto")
	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto,
			@RequestParam(name = "buffetScelto") Long id,
			BindingResult bindingResult, Model model) {
		
		this.piattoValidator.valB(id, bindingResult);
		this.piattoValidator.validate(piatto, bindingResult);
		if(!bindingResult.hasErrors()) {
			
			Buffet buffet = this.buffetService.findById(id);
			
			piatto.setBuffet(buffet);
			
			buffet.getListaPiatti().add(piatto);
			
			this.buffetService.inserisci(buffet);
			
			model.addAttribute("piatto", piatto);
			model.addAttribute("buffet", piatto.getBuffet());
			model.addAttribute("ingredientiDelPiatto", piatto.getIngredientiDelPiatto());
			return "piatto.html";
		}
		else {
			model.addAttribute("buffetDisponibili", this.buffetService.findAll());
			return "piattoForm.html";
		}
	}
	
	@PostMapping("/admin/piattoUpdate/{id}")
	public String updatePiatto(@Valid @ModelAttribute("piatto") Piatto piatto,
			@RequestParam(name = "buffetScelto") Long id,
			BindingResult bindingResult, Model model) {
		
		this.piattoValidator.validate(piatto, bindingResult);
		if(!bindingResult.hasErrors()) {
			
			Buffet BNuovo = this.buffetService.findById(id);
			Buffet BVecchio = piatto.getBuffet();
			
			if(BVecchio != null) {
				for(Piatto pInList : BVecchio.getListaPiatti()) {
					if(pInList.getId() == piatto.getId()) {
						BVecchio.getListaPiatti().remove(pInList);
					}
				}
			}
			
			piatto.setBuffet(BNuovo);
			BNuovo.getListaPiatti().add(piatto);
			
			this.buffetService.inserisci(BNuovo);
			
			this.piattoService.inserisci(piatto);
			model.addAttribute("piatto", piatto);
			model.addAttribute("buffet", piatto.getBuffet());
			model.addAttribute("ingredientiDelPiatto", piatto.getIngredientiDelPiatto());
			return "piatto.html";
		}
		else {
			model.addAttribute("buffetDisponibili", this.buffetService.findAll());
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
		model.addAttribute("buffet", piatto.getBuffet());
		model.addAttribute("ingredientiDelPiatto", piatto.getIngredientiDelPiatto());
		return "piatto.html";
	}
	
	@GetMapping("/admin/piattoForm")
	public String getPiattoForm(Model model) {
		Piatto piatto = new Piatto();
		model.addAttribute("piatto", piatto);
		model.addAttribute("buffetDisponibili", this.buffetService.findAll());
		return "piattoForm.html";
	}
	
	@GetMapping("/admin/deletePiatto")
	public String deletePiatto(@RequestParam Long piattoId) {
		this.piattoService.rimuovi(piattoId);
		return "redirect:/elencoPiatti";
	}
	
	@GetMapping("/admin/updatePiatto/{id}")
	public String updatePiattoForm(@PathVariable("id") Long piattoId, Model model) {
		Piatto piatto = this.piattoService.findById(piattoId);
		model.addAttribute("piatto", piatto);
		model.addAttribute("buffetDisponibili", this.buffetService.findAll());
		return "piattoUpdateForm.html";
	}
}
