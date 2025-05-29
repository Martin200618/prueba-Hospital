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

@Entity(name="historial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="historialId", nullable = false)
        private int historialId;
    @Column(name="correo", nullable = false)
        private patient correo;
    @Column(name="tiempo", nullable = false)
        private medicine tiempo;
}