package com.fiuni.sd.dto.usuario;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
	@NotBlank
	private String email;

	@NotBlank
	private String password;
}
