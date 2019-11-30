package com.tusrutas.app.models.services;

import java.util.ArrayList;

import com.tusrutas.app.models.entity.RecorridoRuta;

public interface IRecorridoRutaService {

	public ArrayList<RecorridoRuta> recorridoRuta(String latitud, String Longitud);
}
