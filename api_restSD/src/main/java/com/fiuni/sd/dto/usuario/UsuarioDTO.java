package com.fiuni.sd.dto.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
