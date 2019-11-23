package com.tusrutas.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the distrito database table.
 * 
 */
@Entity
@NamedQuery(name="Distrito.findAll", query="SELECT d FROM Distrito d")
public class Distrito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DISTRITO")
	private int idDistrito;

	private String nombre;

	//bi-directional many-to-one association to DireccionDestino
	@OneToMany(mappedBy="distrito")
	private List<DireccionDestino> direccionDestinos;

	//bi-directional many-to-one association to DireccionOrigen
	@OneToMany(mappedBy="distrito")
	private List<DireccionOrigen> direccionOrigens;

	//bi-directional many-to-one association to Provicia
	@ManyToOne
	private Provicia provicia;

	public Distrito() {
	}

	public int getIdDistrito() {
		return this.idDistrito;
	}

	public void setIdDistrito(int idDistrito) {
		this.idDistrito = idDistrito;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DireccionDestino> getDireccionDestinos() {
		return this.direccionDestinos;
	}

	public void setDireccionDestinos(List<DireccionDestino> direccionDestinos) {
		this.direccionDestinos = direccionDestinos;
	}

	public DireccionDestino addDireccionDestino(DireccionDestino direccionDestino) {
		getDireccionDestinos().add(direccionDestino);
		direccionDestino.setDistrito(this);

		return direccionDestino;
	}

	public DireccionDestino removeDireccionDestino(DireccionDestino direccionDestino) {
		getDireccionDestinos().remove(direccionDestino);
		direccionDestino.setDistrito(null);

		return direccionDestino;
	}

	public List<DireccionOrigen> getDireccionOrigens() {
		return this.direccionOrigens;
	}

	public void setDireccionOrigens(List<DireccionOrigen> direccionOrigens) {
		this.direccionOrigens = direccionOrigens;
	}

	public DireccionOrigen addDireccionOrigen(DireccionOrigen direccionOrigen) {
		getDireccionOrigens().add(direccionOrigen);
		direccionOrigen.setDistrito(this);

		return direccionOrigen;
	}

	public DireccionOrigen removeDireccionOrigen(DireccionOrigen direccionOrigen) {
		getDireccionOrigens().remove(direccionOrigen);
		direccionOrigen.setDistrito(null);

		return direccionOrigen;
	}

	public Provicia getProvicia() {
		return this.provicia;
	}

	public void setProvicia(Provicia provicia) {
		this.provicia = provicia;
	}

}