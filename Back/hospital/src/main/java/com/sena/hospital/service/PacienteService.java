package com.sena.hospital.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.sena.hospital.DTO.PacienteDTO;
import com.sena.hospital.model.Paciente;
import com.sena.hospital.repository.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    // Obtener todos los pacientes
    public List<PacienteDTO> obtenerTodos() {
        return pacienteRepository.findAll().stream().map(this::converToDTO).collect(Collectors.toList());
    }

    // Obtener un paciente por ID
    public PacienteDTO obtenerPorId(Long id) {
        return pacienteRepository.findById(id).map(this::converToDTO).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    // Registrar un nuevo paciente
    public PacienteDTO guardarPaciente(PacienteDTO dto) {
        Paciente paciente = converToModel(dto);
        return converToDTO(pacienteRepository.save(paciente));
    }

    // Editar un paciente existente
    public PacienteDTO actualizarPaciente(Long id, PacienteDTO dto) {
        Paciente pacienteExistente = pacienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        pacienteExistente.setNombre(dto.getNombre());
        pacienteExistente.setEmail(dto.getEmail());
        pacienteExistente.setRecibirNotificaciones(dto.isRecibirNotificaciones());

        return converToDTO(pacienteRepository.save(pacienteExistente));
    }

    // Eliminar un paciente
    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    private PacienteDTO converToDTO(Paciente paciente) {
        return new PacienteDTO(
            paciente.getId(), 
            paciente.getNombre(), 
            paciente.getEmail(),
            paciente.isRecibirNotificaciones()
        );
    }

    private Paciente converToModel(PacienteDTO dto) {
        return new Paciente(
            dto.getId(), 
            dto.getNombre(), 
            dto.getEmail(), 
            dto.isRecibirNotificaciones()
        );
    }
}