package com.tusrutas.app.models.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.tusrutas.app.models.entity.DireccionDestino;
import com.tusrutas.app.models.entity.RecorridoRuta;
import com.tusrutas.app.models.entity.Ruta;

@Service
public class RutaServiceImpl implements IRutaService {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ruta> obtenerRuta(List<Integer> id) {
		
		List<Ruta> rutas =  new ArrayList<Ruta>();
		try {
			String query = "Select c1 from Ruta c1 WHERE c1.idruta IN :id";
						
			rutas = em.createQuery(query).setParameter("id", id).getResultList();
			
			return rutas;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
