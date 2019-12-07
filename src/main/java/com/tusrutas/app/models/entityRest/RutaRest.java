package com.tusrutas.app.models.entityRest;

import java.io.Serializable;

public class RutaRest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idruta;
	private String empresa;
	private String nombre;
	
	public int getIdruta() {
		return idruta;
	}
	public void setIdruta(int idruta) {
		this.idruta = idruta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
		
}
