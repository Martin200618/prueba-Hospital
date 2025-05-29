package com.sena.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.hospital.model.patient;

public interface Ipatient extends JpaRepository<patient, Integer>{
}