package com.tusrutas.app.models.services;

import java.util.List;

import com.tusrutas.app.models.entity.Ruta;

public interface IRutaService {

	public List<Ruta> obtenerRuta(List<Integer> id);
}
