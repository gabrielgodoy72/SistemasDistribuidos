package com.fiuni.sd;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.service.usuario.IUsuarioService;

@SpringBootTest
class ApiRestApplicationTests {

	@Autowired
	private IUsuarioService usuarioService;

	/**
	 * Crea un usuario con rol por defecto ROLE_USER
	 */
	/*@Test
	void createUsuarioTest() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setNombre("User");
		dto.setApellido("User");
		dto.setEmail("user@email.com");
		dto.setUsername("user");
		dto.setPassword("user");
		UsuarioDTO retorno = usuarioService.save(dto);
		assertNotNull(retorno);
	}*/
	
	@Test
	void getUsuarioRolesTest() {
		UsuarioDTO retorno = usuarioService.getById(1);
		assertTrue(retorno.getRoles_id().size() == 2);
	}

}
