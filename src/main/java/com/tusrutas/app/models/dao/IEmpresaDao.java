package com.tusrutas.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.tusrutas.app.models.entity.Empresa;

public interface IEmpresaDao extends CrudRepository<Empresa, Integer>{

}
