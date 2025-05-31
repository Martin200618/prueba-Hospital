package com.sena.hospital.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "PacienteMedicamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteMedicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;
    
    private LocalDateTime proximoRecordatorio; // Fecha y hora del próximo recordatorio
    private boolean suspendido; // Si el recordatorio está suspendido

    @OneToMany(mappedBy = "pacienteMedicamento")
    private List<HistorialRecordatorio> historial;
}