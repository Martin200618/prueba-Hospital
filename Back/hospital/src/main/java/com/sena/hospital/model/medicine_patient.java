package com.sena.hospital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="medicine_patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class medicine_patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="medicine_patientId", nullable = false)
        private int medicine_patientId;
    @Column(name="patientId", nullable = false)
        private patient patientId;
    @Column(name="medicineId", nullable = false)
        private medicine medicineId;
}