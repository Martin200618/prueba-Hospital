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

@Entity(name="patient")
@Getter 
@Setter 
@AllArgsConstructor
@NoArgsConstructor
public class patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PatientId")
        private int PatientId;
    @Column(name="nombre")
        private String nombre;
    @Column(name="correo")
        private String correo;
    @Column(name="confirmacion")
        private aceptacion confirmacion;
}