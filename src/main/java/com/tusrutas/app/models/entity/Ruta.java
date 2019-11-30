package com.tusrutas.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ruta database table.
 * 
 */
@Entity
@NamedQuery(name="Ruta.findAll", query="SELECT r FROM Ruta r")
public class Ruta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idruta;

	@Column(name="EMPRESA_RUTA")
	private String empresaRuta;

	@Column(name="NOMBRE_RUTA")
	private String nombreRuta;

	//bi-directional many-to-one association to RecorridoRuta
	@OneToMany(mappedBy="ruta")
	private List<RecorridoRuta> recorridoRutas;

	public Ruta() {
	}

	public int getIdruta() {
		return this.idruta;
	}

	public void setIdruta(int idruta) {
		this.idruta = idruta;
	}

	public String getEmpresaRuta() {
		return this.empresaRuta;
	}

	public void setEmpresaRuta(String empresaRuta) {
		this.empresaRuta = empresaRuta;
	}

	public String getNombreRuta() {
		return this.nombreRuta;
	}

	public void setNombreRuta(String nombreRuta) {
		this.nombreRuta = nombreRuta;
	}

	public List<RecorridoRuta> getRecorridoRutas() {
		return this.recorridoRutas;
	}

	public void setRecorridoRutas(List<RecorridoRuta> recorridoRutas) {
		this.recorridoRutas = recorridoRutas;
	}

	public RecorridoRuta addRecorridoRuta(RecorridoRuta recorridoRuta) {
		getRecorridoRutas().add(recorridoRuta);
		recorridoRuta.setRuta(this);

		return recorridoRuta;
	}

	public RecorridoRuta removeRecorridoRuta(RecorridoRuta recorridoRuta) {
		getRecorridoRutas().remove(recorridoRuta);
		recorridoRuta.setRuta(null);

		return recorridoRuta;
	}

}