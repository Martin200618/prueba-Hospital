package com.sena.hospital.DTO;

import com.sena.hospital.model.aceptacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class patientDTO {
    private String nombre;
    private String correo;
    private aceptacion confirmacion;
}