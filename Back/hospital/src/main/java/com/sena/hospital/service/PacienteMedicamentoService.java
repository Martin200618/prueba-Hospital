package com.sena.hospital.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.sena.hospital.DTO.PacienteMedicamentoDTO;
import com.sena.hospital.model.PacienteMedicamento;
import com.sena.hospital.repository.PacienteMedicamentoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteMedicamentoService {
    private PacienteMedicamentoRepository pivoteRepository;

    public List<PacienteMedicamentoDTO> obtenerTodos() {
        return pivoteRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public PacienteMedicamentoDTO obtenerPorId(Long id) {
        return pivoteRepository.findById(id).map(this::convertToDTO).orElseThrow(() -> new RuntimeException("Asociación no encontrada"));
    }

    public PacienteMedicamentoDTO guardarAsociacion(PacienteMedicamentoDTO dto) {
        PacienteMedicamento asociacion = convertToModel(dto);
        return convertToDTO(pivoteRepository.save(asociacion));
    }

    public PacienteMedicamentoDTO actualizarAsociacion(Long id, PacienteMedicamentoDTO dto) {
        PacienteMedicamento entidadExistente = pivoteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Asociación no encontrada"));

        entidadExistente.setProximoRecordatorio(dto.getProximoRecordatorio());
        entidadExistente.setSuspendido(dto.isSuspendido());

        return convertToDTO(pivoteRepository.save(entidadExistente));
    }

    public void eliminarAsociacion(Long id) {
        pivoteRepository.deleteById(id);
    }

    private PacienteMedicamentoDTO convertToDTO(PacienteMedicamento entidad) {
        // Update the constructor parameters to match the actual PacienteMedicamentoDTO constructor
        PacienteMedicamentoDTO dto = new PacienteMedicamentoDTO();
        dto.setId(entidad.getId());
        dto.setProximoRecordatorio(entidad.getProximoRecordatorio());
        dto.setSuspendido(entidad.isSuspendido());
        return dto;
    }

    private PacienteMedicamento convertToModel(PacienteMedicamentoDTO dto) {
        PacienteMedicamento entidad = new PacienteMedicamento();
        entidad.setId(dto.getId());
        entidad.setProximoRecordatorio(dto.getProximoRecordatorio());
        entidad.setSuspendido(dto.isSuspendido());
        return entidad;
    }
}