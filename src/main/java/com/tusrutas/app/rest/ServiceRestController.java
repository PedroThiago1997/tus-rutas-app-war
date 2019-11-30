package com.tusrutas.app.rest;

import java.io.IOException;
import java.util.HashMap;
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
import com.tusrutas.app.models.services.IDireccionDestinoService;
import com.tusrutas.app.models.services.IDireccionOrigenService;

@RestController
public class ServiceRestController {

	@Autowired(required = true)
	private IDireccionOrigenService direccionService;

	@Autowired(required = true)
	private IDireccionDestinoService direccionDestinoService;

	DireccionOrigen origen;
	DireccionDestino destino;

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
			if(origen == null) {
				response.put("respuesta", "Origen fuera de alcance");
			}else {
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
			if(destino == null) {
				response.put("respuesta", "Destino fuera de alcance");
			}else {
				response.put("direccion", destino.getDireccion());
				response.put("latitud", destino.getLatitud());
				response.put("longitud", destino.getLongitud());
			}
			return ResponseEntity.accepted().body(response);
		} else {
			Map<String, String> response = new HashMap<>();
			response.put("error", "Opcion Incorrecta");
			return ResponseEntity.accepted().body(response);
		}
			

	}

}
