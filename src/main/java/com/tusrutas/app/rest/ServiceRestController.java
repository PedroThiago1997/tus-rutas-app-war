package com.tusrutas.app.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tusrutas.app.models.entity.DireccionDestino;
import com.tusrutas.app.models.entity.DireccionOrigen;
import com.tusrutas.app.models.entity.RecorridoRuta;
import com.tusrutas.app.models.entity.Ruta;
import com.tusrutas.app.models.entityRest.RecorridoRest;
import com.tusrutas.app.models.entityRest.RutaRest;
import com.tusrutas.app.models.services.IDireccionDestinoService;
import com.tusrutas.app.models.services.IDireccionOrigenService;
import com.tusrutas.app.models.services.IRecorridoRutaService;
import com.tusrutas.app.models.services.IRutaService;

@RestController
public class ServiceRestController {

	@Autowired(required = true)
	private IDireccionOrigenService direccionService;

	@Autowired(required = true)
	private IDireccionDestinoService direccionDestinoService;

	@Autowired
	private IRecorridoRutaService recorridoRutaService;

	@Autowired
	private IRutaService rutaService;

	DireccionOrigen origen;
	DireccionDestino destino;
	List<RecorridoRuta> recorridos;
	List<Ruta> rutas;

	@RequestMapping(value = "/webhook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> consulta(@RequestBody String jsonString) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(jsonString);
		String referencia = node.get("referencia").asText();
		int opcion = node.get("opcion").asInt();
		if (opcion == 1) {
			origen = new DireccionOrigen();
			origen = direccionService.obtenerOrigen(referencia);
			Map<String, String> response = new HashMap<>();
			if (origen == null) {
				response.put("respuesta", "Origen fuera de alcance");
			} else {
				response.put("direccion", origen.getDireccion());
				response.put("latitud", origen.getLatitud());
				response.put("longitud", origen.getLongitud());
			}
			return ResponseEntity.accepted().body(response);
		}
		if (opcion == 2) {
			destino = new DireccionDestino();
			destino = direccionDestinoService.obtenerDestino(referencia);
			Map<String, String> response = new HashMap<>();
			if (destino == null) {
				response.put("respuesta", "Destino fuera de alcance");
			} else {
				response.put("direccion", destino.getDireccion());
				response.put("latitud", destino.getLatitud());
				response.put("longitud", destino.getLongitud());
			}
			return ResponseEntity.accepted().body(response);
		}
		if (opcion == 3) {
			Map<String, Object> response = new HashMap<>();
			recorridos = new ArrayList<RecorridoRuta>();
			recorridos = recorridoRutaService.recorridoRuta(node.get("origen").asText(), node.get("destino").asText());
			List<RecorridoRest> reco = new ArrayList<RecorridoRest>();
			RecorridoRest r;
			if (recorridos.size() > 0) {
				for (int i = 0; i < recorridos.size(); i++) {
					r = new RecorridoRest();
					r.setIdRecorrido(recorridos.get(i).getIdRecorrido());
					r.setLatitud(recorridos.get(i).getLatitud());
					r.setLongitud(recorridos.get(i).getLongitud());
					r.setReferencia(recorridos.get(i).getReferencia());
					r.setIdRuta(recorridos.get(i).getRuta().getIdruta());
					reco.add(r);
				}
				List<Integer> id = new ArrayList<Integer>();
				for (int i = 0; i < reco.size(); i++) {
					id.add(reco.get(i).getIdRuta());
				}
				rutas = rutaService.obtenerRuta(id);
				List<RutaRest> rut = new ArrayList<RutaRest>();
				RutaRest ru;
				if (rutas.size() > 0) {
					for (Ruta ruta : rutas) {
						ru = new RutaRest();
						ru.setIdruta(ruta.getIdruta());
						ru.setEmpresa(ruta.getEmpresa().getNombre());
						ru.setNombre(ruta.getNombreRuta());
						rut.add(ru);
					}
					response.put("Rutas", rut);
				}
			} else {
				response.put("error", "No se encontraron rutas");
			}
			return ResponseEntity.accepted().body(response);
		} else {
			Map<String, String> response = new HashMap<>();
			response.put("error", "Opcion Incorrecta");
			return ResponseEntity.accepted().body(response);
		}

	}

}
