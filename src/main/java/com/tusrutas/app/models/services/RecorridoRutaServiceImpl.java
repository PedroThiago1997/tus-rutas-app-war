package com.tusrutas.app.models.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.tusrutas.app.models.entity.RecorridoRuta;

@Service
public class RecorridoRutaServiceImpl implements IRecorridoRutaService {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<RecorridoRuta> recorridoRuta(String referenciaorigen, String referenciadestino) {

		List<RecorridoRuta> recorridos = new ArrayList<RecorridoRuta>();
		List<RecorridoRuta> origenes = new ArrayList<RecorridoRuta>();
		List<RecorridoRuta> destinos = new ArrayList<RecorridoRuta>();

		try {
			String query1 = "Select u from RecorridoRuta u where u.referencia LIKE :referenciaorigen";
			String query2 = "Select u from RecorridoRuta u where u.referencia LIKE :referenciadestino";

			origenes = em.createQuery(query1).setParameter("referenciaorigen", "%" + referenciaorigen + "%")
					.getResultList();
			destinos = em.createQuery(query2).setParameter("referenciadestino", "%" + referenciadestino + "%")
					.getResultList();

			for (RecorridoRuta recorridoRuta : origenes) {
				for (RecorridoRuta recorridoRuta2 : destinos) {
					if (recorridoRuta.getRuta().getIdruta() == recorridoRuta2.getRuta().getIdruta()) {
						recorridos.add(recorridoRuta);
					}
				}
			}
			return recorridos;
		} catch (Exception e) {
			return null;
		}
	}
}
