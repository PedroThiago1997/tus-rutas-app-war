package com.tusrutas.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the direccion_origen database table.
 * 
 */
@Entity
@Table(name="direccion_origen")
@NamedQuery(name="DireccionOrigen.findAll", query="SELECT d FROM DireccionOrigen d")
public class DireccionOrigen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ORIGEN")
	private Long idOrigen;

	private String direccion;

	private String latitud;

	private String longitud;

	//bi-directional many-to-one association to Distrito
	@ManyToOne
	private Distrito distrito;

	public DireccionOrigen() {
	}

	public Long getIdOrigen() {
		return this.idOrigen;
	}

	public void setIdOrigen(Long idOrigen) {
		this.idOrigen = idOrigen;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLatitud() {
		return this.latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return this.longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public Distrito getDistrito() {
		return this.distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	@Override
	public String toString() {
		return "DireccionOrigen [idOrigen=" + idOrigen + ", direccion=" + direccion + ", latitud=" + latitud
				+ ", longitud=" + longitud + ", distrito=" + distrito + "]";
	}
	
	

}