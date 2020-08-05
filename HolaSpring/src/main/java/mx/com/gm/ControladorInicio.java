package mx.com.gm;

import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
public class ControladorInicio {

	@GetMapping("/")
	public String inicio() {
		log.info("ejecutando el controlador rest");
		return "Hola Mundo con Spring";
		
	}
}
