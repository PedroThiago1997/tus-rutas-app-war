package com.tusrutas.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.tusrutas.app.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
}
