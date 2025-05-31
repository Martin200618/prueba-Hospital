package com.sena.hospital.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.sena.hospital.DTO.MedicamentoDTO;
import com.sena.hospital.model.Medicamento;
import com.sena.hospital.repository.MedicamentoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicamentoService {
    private MedicamentoRepository medicamentoRepository;

    public List<MedicamentoDTO> obtenerTodos() {
        return medicamentoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public MedicamentoDTO obtenerPorId(Long id) {
        return medicamentoRepository.findById(id).map(this::convertToDTO).orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
    }

    public MedicamentoDTO guardarMedicamento(MedicamentoDTO dto) {
        Medicamento medicamento = convertToModel(dto);
        return convertToDTO(medicamentoRepository.save(medicamento));
    }

    public MedicamentoDTO actualizarMedicamento(Long id, MedicamentoDTO dto) {
        Medicamento medicamentoExistente = medicamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));

        medicamentoExistente.setNombre(dto.getNombre());
        medicamentoExistente.setDosis(dto.getDosis());
        medicamentoExistente.setHorario(dto.getHorario());

        return convertToDTO(medicamentoRepository.save(medicamentoExistente));
    }

    public void eliminarMedicamento(Long id) {
        medicamentoRepository.deleteById(id);
    }

    private MedicamentoDTO convertToDTO(Medicamento medicamento) {
        return new MedicamentoDTO(
            medicamento.getId(), 
            medicamento.getNombre(), 
            medicamento.getDosis(), 
            medicamento.getHorario()
        );
    }

    private Medicamento convertToModel(MedicamentoDTO dto) {
        return new Medicamento(
            dto.getId(), 
            dto.getNombre(), 
            dto.getDosis(), 
            dto.getHorario()
        );
    }
}