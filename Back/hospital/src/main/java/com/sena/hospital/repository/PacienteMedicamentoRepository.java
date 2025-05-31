package com.sena.hospital.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.hospital.model.PacienteMedicamento;

@Repository
public interface PacienteMedicamentoRepository extends JpaRepository<PacienteMedicamento, Long> {
    List<PacienteMedicamento> findBySuspendidoFalseAndProximoRecordatorioBefore(LocalDateTime fecha);
}