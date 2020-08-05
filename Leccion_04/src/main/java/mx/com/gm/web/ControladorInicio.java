package mx.com.gm.web;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.var;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.servicio.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private PersonaService personaService; //Inyectar una instancia de la clase PersonaServiceImplementation
    
    @GetMapping("/") //mapeo
    public String inicio(Model model) {
    	var personas = personaService.listarPersonas();
        log.info("Ejecutando el controlador Spring MVC");
        model.addAttribute("personas", personas);
        return "index";
    }
}
