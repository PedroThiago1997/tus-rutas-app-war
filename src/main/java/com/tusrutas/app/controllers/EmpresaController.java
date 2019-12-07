package com.tusrutas.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tusrutas.app.models.dao.IEmpresaDao;
import com.tusrutas.app.models.entity.Empresa;

@Controller
@SessionAttributes("empresa")
public class EmpresaController {
	
	@Autowired
	private IEmpresaDao empresaDao;
	
	@RequestMapping(value = "/dashboard/empresas", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Empresas");
		model.addAttribute("empresas", empresaDao.findAll());
		return "/dashboard/empresas";
	}
	
	@RequestMapping(value="/dashboard/empresas/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") int id) {
		if(id > 0) {
			empresaDao.deleteById(id);
			//return "/dashboard/empresas";
		}
		return "redirect:/dashboard/empresas";
	}
	
	@RequestMapping(value = "/dashboard/empresas/crear")
	public String crear(Map<String, Object> model) {
		Empresa empresa = new Empresa();
		model.put("empresa", empresa);
		return "/dashboard/empresas";
	}
	@RequestMapping(value = "/dashboard/empresas/crear", method = RequestMethod.POST)
	public String guardar(@Valid Empresa empresa, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Empresa");
			return "/dashboard/empresas";
		}
		empresaDao.save(empresa);
		status.setComplete();
		return "redirect:/dashboard/empresas";
	}
}
