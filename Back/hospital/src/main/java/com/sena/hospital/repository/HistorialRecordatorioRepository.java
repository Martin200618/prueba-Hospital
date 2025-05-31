package com.sena.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.hospital.model.HistorialRecordatorio;

@Repository
public interface HistorialRecordatorioRepository extends JpaRepository<HistorialRecordatorio, Long> {
}