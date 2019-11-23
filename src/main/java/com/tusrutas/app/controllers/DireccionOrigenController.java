package com.tusrutas.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tusrutas.app.models.entity.DireccionOrigen;
import com.tusrutas.app.models.services.IDireccionOrigenService;

@Controller
@SessionAttributes("origen")
public class DireccionOrigenController {

	@Autowired(required = true)
	private IDireccionOrigenService direccionService;
	
	@RequestMapping(value = "/origenes")
	public String origen(Map<String, Object> model) {
		DireccionOrigen origen = new DireccionOrigen();
		model.put("origen", origen);
		return "origen";
	}
	
	@RequestMapping(value = "/origenes", method = RequestMethod.POST)
	public String verificar(@Validated DireccionOrigen origen, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			return "origen";
		}
		try {
			origen = direccionService.obtenerOrigen(origen.getDireccion());
			
			if(origen.equals(null)) {
				System.out.println("Origen fuera de alcance");
				return "redirect:/origen";
			} else {
				System.out.println("Origen dentro de alcance: " + origen);
				return "redirect:/origen";
			}
		} catch (Exception e) {
			return "redirect:/origen";
		}
		
	}
}
