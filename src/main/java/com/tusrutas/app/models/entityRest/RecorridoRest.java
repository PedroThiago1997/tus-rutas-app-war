package com.tusrutas.app.models.entityRest;

import java.io.Serializable;

public class RecorridoRest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idRecorrido;
	private String latitud;
	private String longitud;
	private String referencia;
	private int idRuta;
	
	public int getIdRecorrido() {
		return idRecorrido;
	}
	public void setIdRecorrido(int idRecorrido) {
		this.idRecorrido = idRecorrido;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public int getIdRuta() {
		return idRuta;
	}
	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}
	
	
}
