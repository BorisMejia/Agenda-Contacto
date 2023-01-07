package com.agenda.contactos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agenda.contactos.modelo.Contacto;
import com.agenda.contactos.repositorio.ContactoRepositorio;

@Controller
public class ContactoController {
	
	@Autowired
	private ContactoRepositorio conRepositorio;
	
	@GetMapping({"/",""})
	public String verPaginaInicio(Model model) {
		List<Contacto> contactos = conRepositorio.findAll(); 
		model.addAttribute("contactos", contactos);
		return "index";
	}
	
	@GetMapping("/nuevo")
	public String mostrarFormulario(Model model) {
		model.addAttribute("contacto", new Contacto());
		return "nuevo";
	}
	
	@PostMapping("/nuevo")
	public String guardarContacto(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirect, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("contacto", contacto);
		}
		
		conRepositorio.save(contacto);
		redirect.addFlashAttribute("msgExito", "El contacto se agregó correctamente");
		return "redirect:/"; 
	}
	
	

}
