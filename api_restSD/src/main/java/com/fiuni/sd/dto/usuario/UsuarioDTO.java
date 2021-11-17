package com.fiuni.sd.dto.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "user_register")
public class UsuarioDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	private String nombre;

	@NotNull
	@NotEmpty
	private String apellido;

	@NotNull
	@NotEmpty
	@Email
	private String email;

	@NotNull
	@NotEmpty
	private String password;

}
