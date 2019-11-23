package com.tusrutas.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
/**
 * The persistent class for the direccion_destino database table.
 * 
 */
@Entity
@Table(name="direccion_destino")
@NamedQuery(name="DireccionDestino.findAll", query="SELECT d FROM DireccionDestino d")
public class DireccionDestino implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DESTINO")
	private int idDestino;

	private String direccion;

	private String latitud;

	private String longitud;

	//bi-directional many-to-one association to Distrito
	@ManyToOne
	private Distrito distrito;

	public DireccionDestino() {
	}

	public int getIdDestino() {
		return this.idDestino;
	}

	public void setIdDestino(int idDestino) {
		this.idDestino = idDestino;
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

}