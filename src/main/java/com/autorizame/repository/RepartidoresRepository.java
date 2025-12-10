package com.autorizame.repository;

import java.util.List;
import java.util.Optional;

import com.autorizame.models.entity.Repartidor;

public interface RepartidoresRepository {

	Repartidor guardar(Repartidor repartidor);
	Optional<Repartidor> buscarPorCorreo(String correo);
	List<Repartidor> listarRepartidoresPorEmpresa(Long id);
	Optional<Repartidor> buscarPorID(Long id);
	
}
