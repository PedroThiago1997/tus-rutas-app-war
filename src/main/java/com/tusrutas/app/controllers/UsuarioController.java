package com.tusrutas.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tusrutas.app.models.entity.Usuario;
import com.tusrutas.app.models.services.IUsuarioService;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {

	@Autowired(required = true)
	private IUsuarioService usuarioService;

	@RequestMapping(value = "/index")
	public void index() {

	}

	@RequestMapping(value = "/login")
	public String login(Map<String, Object> model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo", "Formulario de Usuario");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Validated Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			return "login";
		}
		try {
			usuario = usuarioService.loginUsuario(usuario.getCorreo(), usuario.getPassword());
			
			if(usuario.equals(null)) {
				System.out.println("Datos incorrectos");
				return "redirect:/login";
			} else {
				System.out.println("Bienvenido: " + usuario);
				return "redirect:/index";
			}
		} catch (Exception e) {
			return "redirect:/login";
		}
		
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Usuarios");
		model.addAttribute("clientes", usuarioService.findAll());
		return "listar";
	}

	@RequestMapping(value = "/registro")
	public String crear(Map<String, Object> model) {

		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo", "Formulario de Cliente");
		return "registro";
	}

	@RequestMapping(value = "/registro/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Usuario usuario = null;

		if (id > 0) {
			usuario = usuarioService.findOne(id);
		} else {
			return "redirect:/listar";
		}
		model.put("cliente", usuario);
		model.put("titulo", "Editar Cliente");
		return "index";
	}

	@RequestMapping(value = "/registro", method = RequestMethod.POST)
	public String guardar(@Validated Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}

		usuarioService.save(usuario);
		status.setComplete();
		return "redirect:index";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			usuarioService.delete(id);
		}
		return "redirect:/listar";
	}
}
