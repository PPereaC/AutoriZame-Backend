package com.autorizame.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.autorizame.models.entity.Repartidor;
import com.autorizame.repository.RepartidoresRepository;

@Repository
public class RepartidoresRepositoryEnMemoria implements RepartidoresRepository {
	
	private Map<Long, Repartidor> repartidores = new HashMap<>();
    private Long contador = 1L;

	@Override
	public Repartidor guardar(Repartidor repartidor) {
		
		// Si el repartidor llega sin ID (es nuevo), se le asigna uno nuevo
		if (repartidor.getId() == null) {
			repartidor.setId(contador);
			contador++;
		}
		
		// Se guarda el repartidor con su id en el Map
		repartidores.put(repartidor.getId(), repartidor);
						
		return repartidor;
		
	}

	@Override
	public Optional<Repartidor> buscarPorCorreo(String correo) {
		
		// Se busca si hay algún repartidor con el correo pasado por parámetro
		for (Repartidor r : repartidores.values()) {
			if(r.getCorreo().equalsIgnoreCase(correo)) {
				return Optional.of(r);
			}
		}
				
		// Si no se encuentra se manda como vacío
		return Optional.empty();
		
	}

	@Override
	public List<Repartidor> listarRepartidoresPorEmpresa(Long id) {
		
		List<Repartidor> repartidoresEncontrados = new ArrayList<>();
		
		for (Repartidor r : repartidores.values()) {
			if(r.getEmpresaId().equals(id)) {
				repartidoresEncontrados.add(r);
			}
		}
		
		return repartidoresEncontrados;
	}

	@Override
	public Optional<Repartidor> buscarPorID(Long id) {
		
		for (Repartidor r : repartidores.values()) {
			if(r.getId().equals(id)) {
				return Optional.of(r);
			}
		}
		
		return Optional.empty();
	}

}
