package com.sena.hospital.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialRecordatorioDTO {
    private Long id;
    private PacienteDTO paciente;
    private MedicamentoDTO medicamento;
    private LocalDateTime fechaEnvio;
    private boolean confirmado;
}