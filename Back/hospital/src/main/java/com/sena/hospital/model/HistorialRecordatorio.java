package com.sena.hospital.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="historial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistorialRecordatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pacienteId")
    private Paciente pacienteId;

    @ManyToOne
    @JoinColumn(name = "medicamentoId")
    private Medicamento medicamentoId;

    @ManyToOne
    @JoinColumn(name = "pacienteMedicamento_id")
    private PacienteMedicamento pacienteMedicamento;

    private LocalDateTime fechaEnvio; // Fecha en que se envió el recordatorio
    private boolean confirmado; // Si el paciente confirmó la toma del medicamento
}