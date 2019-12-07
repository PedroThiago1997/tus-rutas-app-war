package com.tusrutas.app.models.services;

import java.util.List;

import com.tusrutas.app.models.entity.RecorridoRuta;

public interface IRecorridoRutaService {

	public List<RecorridoRuta> recorridoRuta(String referenciaorigen, String referenciadestino);
}
