package com.tusrutas.app.models.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tusrutas.app.models.dao.IDireccionOrigenDao;
import com.tusrutas.app.models.entity.DireccionOrigen;

@Service
public class DireccionOrigenServiceImpl implements IDireccionOrigenService {

	@Autowired
	private IDireccionOrigenDao direccionDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public DireccionOrigen obtenerOrigen(String referencia) {
		DireccionOrigen direccion =  new DireccionOrigen();
		try {
			String query = "Select u from DireccionOrigen u where u.direccion LIKE :referencia";
			Query q = em.createQuery(query);
			
			q.setParameter("referencia", "%" + referencia + "%");
			
			direccion = (DireccionOrigen)q.getSingleResult();
			
			return direccion;
			
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

}
