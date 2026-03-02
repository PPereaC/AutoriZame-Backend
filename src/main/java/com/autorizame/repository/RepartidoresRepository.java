package com.autorizame.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autorizame.models.entity.Repartidor;

public interface RepartidoresRepository extends JpaRepository<Repartidor, Long> {

	Optional<Repartidor> findByCorreo(String correo);
	List<Repartidor> findByEmpresaId(Long empresaId);
}
