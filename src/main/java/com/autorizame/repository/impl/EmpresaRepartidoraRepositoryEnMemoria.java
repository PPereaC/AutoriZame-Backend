package com.autorizame.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.autorizame.models.entity.EmpresaRepartidora;
import com.autorizame.repository.EmpresaRepository;

@Repository
public class EmpresaRepartidoraRepositoryEnMemoria implements EmpresaRepository {

	private Map<Long, EmpresaRepartidora> empresas = new HashMap<>();
    private Long contador = 1L;
    
	@Override
	public EmpresaRepartidora guardar(EmpresaRepartidora empresa) {
		// Si la empresa llega sin ID (es nueva), se le asigna uno nuevo
		if (empresa.getId() == null) {
			empresa.setId(contador);
			contador++;
		} 
				
		// Se guarda la empresa con su id en el Map
		empresas.put(empresa.getId(), empresa);
				
		return empresa;
	}
	
	@Override
	public Optional<EmpresaRepartidora> buscarPorNombre(String nombre) {
		
		for (EmpresaRepartidora empresa : empresas.values()) {
			if(empresa.getNombre().equalsIgnoreCase(nombre)) {
				return Optional.of(empresa);
			}
		}
		
		return Optional.empty();
		
	}

	@Override
	public List<EmpresaRepartidora> listarEmpresas() {
		
	    return new ArrayList<>(empresas.values());
		
	}

	@Override
	public Optional<EmpresaRepartidora> buscarPorID(Long id) {
		
		for (EmpresaRepartidora e : empresas.values()) {
			if(e.getId().equals(id)) {
				return Optional.of(e);
			}
		}
		
		return Optional.empty();
		
	}

	@Override
	public void borrarEmpresa(Long id) {
		
		empresas.remove(id);
		
	}
	
}
