package com.autorizame.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autorizame.models.entity.Autorizado;

public interface AutorizadoRepository extends JpaRepository<Autorizado, Long> {

	List<Autorizado> findByClienteId(Long clienteId);
	Optional<Autorizado> findByDni(String dni);
	Optional<Autorizado> findByClienteIdAndDniIgnoreCase(Long clienteId, String dni);
}

