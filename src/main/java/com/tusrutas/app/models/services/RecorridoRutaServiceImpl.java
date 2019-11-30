package com.tusrutas.app.models.services;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.tusrutas.app.models.entity.RecorridoRuta;

@Service
public class RecorridoRutaServiceImpl implements IRecorridoRutaService {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RecorridoRuta> recorridoRuta(String latitud, String Longitud) {
		ArrayList<RecorridoRuta> recorridos =  new ArrayList<RecorridoRuta>();
		RecorridoRuta ruta;
		
		try {
			String query = "Select u from RecorridoRuta u where u.latitud LIKE :latitud and u.longitud LIKE :longitud";
			Query q = em.createQuery(query);
			
			q.setParameter("latitud", "%" + latitud + "%");
			q.setParameter("longitud", "%" + Longitud + "%");
			
			recorridos = (ArrayList<RecorridoRuta>)q.getResultList();
			
			return recorridos;
			
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
		
		//return null;
	}

}
