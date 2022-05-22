package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Buffet;
import com.example.demo.service.BuffetService;

@Component
public class BuffetValidator implements Validator{

	@Autowired
	private BuffetService buffetService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.buffetService.alreadyExists((Buffet)o)) {
			errors.reject("chef.duplicato");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Buffet.class.equals(clazz);
	}
}