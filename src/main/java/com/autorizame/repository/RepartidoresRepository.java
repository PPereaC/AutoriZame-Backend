package com.autorizame.repository;

import java.util.Optional;

import com.autorizame.models.entity.Repartidor;

public interface RepartidoresRepository {

	Repartidor guardar(Repartidor repartidor);
	Optional<Repartidor> buscarPorCorreo(String correo);
	
}
