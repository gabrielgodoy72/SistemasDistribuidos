package com.fiuni.sd;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
	@Test
	void createUsuarioTest() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setNombre("Gabriel");
		dto.setApellido("Godoy");
		dto.setEmail("gabriel.godoy@fiuni.edu.py");
		dto.setUsername("gabriel");
		dto.setPassword("gabriel123");
		UsuarioDTO retorno = usuarioService.save(dto);
		assertNotNull(retorno);
	}

}
