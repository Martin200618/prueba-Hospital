package com.sena.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.hospital.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}