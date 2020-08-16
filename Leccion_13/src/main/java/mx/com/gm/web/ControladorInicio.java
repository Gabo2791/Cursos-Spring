package mx.com.gm.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private PersonaService personaService; //Inyectar una instancia de la clase PersonaServiceImplementation
    
    @GetMapping("/") //mapeo de inicio de la pagina 
    public String inicio(Model model, @AuthenticationPrincipal User user ) {
    	var personas = personaService.listarPersonas();
        log.info("Ejecutando el controlador Spring MVC");
        log.info("***-->Usuario que hizo login: " + user);
        model.addAttribute("personas", personas);
        var saldoTotal=0D;
        for (var p:personas) {
        	saldoTotal+=p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        System.out.println("se carga el mapeo de clases");
        return "index";
    }
    
    @GetMapping("/agregar")
    public String agregar(Persona persona) {
    	return "modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores) {
    	if(errores.hasErrors()) {
    	return "modificar";
    	}
    	
    	personaService.guardar(persona);
    	return "redirect:/";
    }
    
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model) {
    	persona = personaService.encontrarPersona(persona);
    	model.addAttribute("persona", persona); //Insertar los valores recuperados en los campos de modificacion
    	return "modificar";
    }
    
    //@GetMapping("/eliminar/{idPersona}")
    @GetMapping("/eliminar") //Procesar el parametro con QueryParameter
    public String eliminar(Persona persona) {
    	personaService.eliminar(persona);
    	return "redirect:/";
    }
}
