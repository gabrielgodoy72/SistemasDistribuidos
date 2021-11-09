package com.fiuni.sd.domain.producto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fiuni.sd.domain.base.BaseDomain;

@Entity
@Table(name = "productos")
public class ProductoDomain implements BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
    private int id;
	
	@Column(name = "descripcion")
    private String descripcion;

    @Column(name = "costo")
    private double costo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "ProductoDomain [id=" + id + ", descripcion=" + descripcion + ", costo=" + costo + "]";
	}
    
}
