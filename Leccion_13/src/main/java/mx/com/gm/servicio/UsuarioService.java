package mx.com.gm.servicio;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.var;
//import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.UsuarioDAO;
import mx.com.gm.domain.Rol;
import mx.com.gm.domain.Usuario;

@Service("userDetailService")
//@Slf4j

public class UsuarioService implements UserDetailsService {
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario=usuarioDao.findByUsername(username);
		if (usuario==null) {
			throw new UsernameNotFoundException(username);
		}
		var roles= new ArrayList<GrantedAuthority>();
		for (Rol rol:usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		return new User(usuario.getUsername(), usuario.getPassword(), roles);
	}
	
}
