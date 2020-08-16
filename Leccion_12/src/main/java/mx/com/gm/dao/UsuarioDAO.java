package mx.com.gm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.com.gm.domain.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long>{
	 Usuario findByUsername(String username);

}
