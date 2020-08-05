package mx.com.gm.web;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.var;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.IPersonaDAO;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private IPersonaDAO personaDao;

    @GetMapping("/") //mapeo
    public String inicio(Model model) {
        var personas = personaDao.findAll();
        log.info("Ejecutando el controlador Spring MVC");
        model.addAttribute("personas", personas);
        return "index";
    }
}
