package com.autorizame.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autorizame.models.entity.EmpresaRepartidora;

public interface EmpresaRepository extends JpaRepository<EmpresaRepartidora, Long> {

	Optional<EmpresaRepartidora> findByNombre(String nombre);
}
