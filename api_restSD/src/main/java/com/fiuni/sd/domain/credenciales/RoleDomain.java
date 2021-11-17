package com.fiuni.sd.domain.credenciales;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fiuni.sd.domain.base.BaseDomain;

//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
@Entity
@Table(name = "roles")
public class RoleDomain implements BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@ManyToMany(mappedBy = "roles")
	private Set<UsuarioDomain> usuarios = new HashSet<UsuarioDomain>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<UsuarioDomain> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<UsuarioDomain> usuarios) {
		this.usuarios = usuarios;
	}
	
}
