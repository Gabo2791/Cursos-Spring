package mx.com.gm.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.gm.dao.PersonaDAO;
import mx.com.gm.domain.Persona;

@Service
public class PersonaServiceImpl implements PersonaService {
	
	@Autowired
	private PersonaDAO personaDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Persona> listarPersonas() { //unicamente leemos informacion de la base de datos
		return (List<Persona>) personaDao.findAll(); //Retorna valores de tipo object. Realizar CAST para recuperar la lista de valores.
		
	}
	@Transactional
	@Override
	public void guardar(Persona persona) { //Transaccion que realiza commit o rollback
		personaDao.save(persona);
	}

	@Override
	@Transactional
	public void eliminar(Persona persona) {
		personaDao.delete(persona);
	}

	@Override
	@Transactional(readOnly=true)
	public Persona encontrarPersona(Persona persona) {
		return personaDao.findById(persona.getIdPersona()).orElseThrow(null);
	}

}
