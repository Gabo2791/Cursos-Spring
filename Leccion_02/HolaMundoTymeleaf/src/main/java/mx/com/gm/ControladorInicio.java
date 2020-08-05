package mx.com.gm;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@Slf4j
public class ControladorInicio {

    @Value("${index.saludo}")
    private String saludo;

    @GetMapping("/") //mapeo
    public String inicio(Model model) {
        var mensaje = "Mensaje con Thymeleaf";
       
        var persona = new Persona();
        persona.setNombre("Victor");
        persona.setApellido("Ramirez");
        persona.setEmail("vramirez.info@gmail.com");
        persona.setTelefono("9711583489");
        
        var persona2 = new Persona();
        persona2.setNombre("Zayra");
        persona2.setApellido("Morales");
        persona2.setEmail("zay_gpe@gmail.com");
        persona2.setTelefono("9612837732");
        
   //     var personas = new ArrayList();
    //    personas.add(persona);
     //   personas.add(persona);
       
        var personas = Arrays.asList(persona, persona2);
     
        log.info("ejecutando el controlador Spring MVC");
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("saludo", saludo);
       // model.addAttribute("persona", persona);
        model.addAttribute("personas", personas );
        return "index";
    }
}
