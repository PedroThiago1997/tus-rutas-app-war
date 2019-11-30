package com.tusrutas.app.models.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.tusrutas.app.models.entity.DireccionDestino;

@Service
public class DireccionDestinoServiceImpl implements IDireccionDestinoService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public DireccionDestino obtenerDestino(String referencia) {
		DireccionDestino direccion =  new DireccionDestino();
		try {
			String query = "Select u from DireccionDestino u where u.direccion LIKE :referencia";
			Query q = em.createQuery(query);
			
			q.setParameter("referencia", "%" + referencia + "%");
			
			direccion = (DireccionDestino)q.getSingleResult();
			
			return direccion;
			
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}


}
