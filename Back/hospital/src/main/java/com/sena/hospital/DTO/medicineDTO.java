package com.sena.hospital.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class medicineDTO {
    private String nombre;
    private String descripcion;
    private double tiempo;
}
