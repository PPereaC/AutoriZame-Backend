package com.autorizame.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.autorizame.models.entity.Autorizado;
import com.autorizame.repository.AutorizadoRepository;

@Repository
public class AutorizadoRepositoryEnMemoria implements AutorizadoRepository {
	
	private Map<Long, Autorizado> autorizados = new HashMap<>();
    private Long contador = 1L;
    
    
	@Override
	public Autorizado guardar(Autorizado autorizado) {
		
		if(autorizado.getId() == null) {
			autorizado.setId(contador);
			contador++;
		}
		
		autorizados.put(autorizado.getId(), autorizado);
		
		return autorizado;
	}
	
	@Override
	public List<Autorizado> buscarPorIDCliente(Long clienteID) {
	    
	    List<Autorizado> listaEncontrada = new ArrayList<>();
	    
	    for (Autorizado autorizado : autorizados.values()) {
	        
	        if (autorizado.getClienteId().equals(clienteID)) {
	            listaEncontrada.add(autorizado);
	        }
	    }
	    
	    return listaEncontrada;
	}
	
	@Override
	public Optional<Autorizado> buscarPorDni(String dni) {
		
		for (Autorizado autorizado : autorizados.values()) {
			if(autorizado.getDni().equalsIgnoreCase(dni)) {
				return Optional.of(autorizado);
			}
		}
		
		return Optional.empty();
	}
	
	@Override
	public Optional<Autorizado> buscarPorID(Long id) {
		
		for (Autorizado autorizado : autorizados.values()) {
			if(autorizado.getId().equals(id)) {
				return Optional.of(autorizado);
			}
		}
		
		return Optional.empty();
	}
	
	@Override
	public void borrarAutorizado(Long clienteID, String dni) {

		Long idEncontrado = null;
		
		for (Autorizado autorizado : autorizados.values()) {
			if(autorizado.getDni().equalsIgnoreCase(dni)) {
				idEncontrado = autorizado.getId();			}
		}
		
		if(idEncontrado != null) {
			autorizados.remove(idEncontrado);			
		}
		
	}

	@Override
	public Optional<Autorizado> buscarPorClienteIdYDni(Long clienteId, String dni) {
	    for (Autorizado a : autorizados.values()) {
	        
	        // Se comprueba la doble coincidencia:
	        boolean mismoCliente = a.getClienteId().equals(clienteId);
	        
	        // Es este DNI??
	        boolean mismoDni = a.getDni().equalsIgnoreCase(dni);
	        
	        // Si cumple AMBAS, es que es un duplicado
	        if (mismoCliente && mismoDni) {
	            return Optional.of(a);
	        }
	    }
	    
	    return Optional.empty();
	}

}
