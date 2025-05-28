package com.sena.hospital.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="medicine")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="medicineID")
        private int medicineID;
    @Column(name="nombre")
        private String nombre;
    @Column(name="descripcion")
        private String descripcion;
    @Column(name="tiempo")
        private LocalTime tiempo;
}
