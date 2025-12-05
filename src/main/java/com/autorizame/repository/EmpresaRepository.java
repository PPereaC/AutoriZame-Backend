package com.autorizame.repository;

import java.util.Optional;

import com.autorizame.models.entity.EmpresaRepartidora;

public interface EmpresaRepository {

	EmpresaRepartidora guardar(EmpresaRepartidora empresa);
	Optional<EmpresaRepartidora> buscarPorNombre(String nombre);
	
}
