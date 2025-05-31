package com.sena.hospital.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteMedicamentoDTO {
    private Long id;
    private PacienteDTO paciente;
    private MedicamentoDTO medicamento;
    private LocalDateTime proximoRecordatorio;
    private boolean suspendido;
}