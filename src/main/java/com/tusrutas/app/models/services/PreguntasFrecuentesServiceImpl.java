package com.tusrutas.app.models.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.tusrutas.app.models.entity.PreguntasFrecuente;

@Service
public class PreguntasFrecuentesServiceImpl implements IPreguntasFrecuentesService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public PreguntasFrecuente obtenerRespuesta(String pregunta) {
		PreguntasFrecuente respuesta =  new PreguntasFrecuente();
		try {
			String query = "Select u from PreguntasFrecuente u where u.pregunta LIKE :pregunta";
			Query q = em.createQuery(query);
			
			q.setParameter("pregunta", "%" + pregunta + "%");
			
			respuesta = (PreguntasFrecuente)q.getSingleResult();
			
			return respuesta;
			
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

}
