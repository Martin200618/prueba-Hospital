package com.sena.hospital.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class medicine_patientDTO {
    private medicineDTO medicineId;
    private patientDTO patientId;
}