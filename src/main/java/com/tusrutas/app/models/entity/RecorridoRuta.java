package com.tusrutas.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the recorrido_ruta database table.
 * 
 */
@Entity
@Table(name="recorrido_ruta")
@NamedQuery(name="RecorridoRuta.findAll", query="SELECT r FROM RecorridoRuta r")
public class RecorridoRuta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idrecorridoruta;

	private String latitud;

	private String longitud;

	//bi-directional many-to-one association to Ruta
	@ManyToOne
	@JoinColumn(name="IDRUTA")
	private Ruta ruta;

	public RecorridoRuta() {
	}

	public int getIdrecorridoruta() {
		return this.idrecorridoruta;
	}

	public void setIdrecorridoruta(int idrecorridoruta) {
		this.idrecorridoruta = idrecorridoruta;
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

	public Ruta getRuta() {
		return this.ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

}