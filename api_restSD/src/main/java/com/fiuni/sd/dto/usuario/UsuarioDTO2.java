package com.fiuni.sd.dto.usuario;

import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

@XmlRootElement(name = "userRes")
public class UsuarioDTO2 extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String nombre;
	private String apellido;
	private String email;
	private String username;
	private String password;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String firstName) {
		this.nombre = firstName;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String lastName) {
		this.apellido = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
