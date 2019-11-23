package com.tusrutas.app.models.services;

import java.util.List;

import com.tusrutas.app.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();

	public void save(Usuario usuario);
	
	public Usuario findOne(Long id);
	
	public void delete(Long id);
	
	public Usuario loginUsuario(String correo, String password);
	
}
