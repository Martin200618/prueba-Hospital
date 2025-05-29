package com.sena.hospital.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class historialDTO {
    private patientDTO correo;
    private medicineDTO tiempo;
}
