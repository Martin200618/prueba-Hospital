package com.sena.hospital.service;

import com.sena.hospital.DTO.HistorialRecordatorioDTO;
import com.sena.hospital.DTO.ResponseDTO;
import com.sena.hospital.DTO.PacienteDTO;
import com.sena.hospital.DTO.MedicamentoDTO;
import com.sena.hospital.model.HistorialRecordatorio;
import com.sena.hospital.repository.HistorialRecordatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistorialService {
    
    @Autowired
    private HistorialRecordatorioRepository historialRepository;// Repositorio para encontrar la relación pivote

    // Crear historial asegurando la relación con PacienteMedicamento
    public ResponseDTO createHistorial(HistorialRecordatorioDTO dto) {
        try {
            HistorialRecordatorio historial = convertToEntity(dto);
            historialRepository.save(historial);
            return new ResponseDTO(HttpStatus.OK.toString(), "Historial creado exitosamente");
        } catch (Exception e) {
            return new ResponseDTO(HttpStatus.BAD_REQUEST.toString(), "Error al crear historial");
        }
    }

    // Obtener todos los registros como DTOs
    public List<HistorialRecordatorioDTO> getAllHistorial() {
        List<HistorialRecordatorio> historiales = historialRepository.findAll();
        return historiales.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Obtener historial por ID
    public Optional<HistorialRecordatorioDTO> getHistorialById(Long id) {
        Optional<HistorialRecordatorio> historialOpt = historialRepository.findById(id);
        return historialOpt.map(this::convertToDTO);
    }

    // Eliminar historial
    public ResponseDTO deleteHistorial(Long id) {
        Optional<HistorialRecordatorio> historialOpt = historialRepository.findById(id);
        if (historialOpt.isEmpty()) {
            return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "Historial no encontrado");
        }
        historialRepository.deleteById(id);
        return new ResponseDTO(HttpStatus.OK.toString(), "Historial eliminado exitosamente");
    }
    
    // Convertir entidad a DTO
    private HistorialRecordatorioDTO convertToDTO(HistorialRecordatorio historial) {
        HistorialRecordatorioDTO dto = new HistorialRecordatorioDTO();
        dto.setId(historial.getId());
        dto.setPaciente(convertToPacienteDTO(historial.getPacienteMedicamento().getPaciente()));
        dto.setMedicamento(convertToMedicamentoDTO(historial.getPacienteMedicamento().getMedicamento()));
        dto.setFechaEnvio(historial.getFechaEnvio());
        dto.setConfirmado(historial.isConfirmado());
        return dto;
    }

    // Convertir entidad Paciente a DTO
    private PacienteDTO convertToPacienteDTO(com.sena.hospital.model.Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        // Agrega aquí otros campos según tu clase PacienteDTO
        return dto;
    }

    // Convertir entidad Medicamento a DTO
    private MedicamentoDTO convertToMedicamentoDTO(com.sena.hospital.model.Medicamento medicamento) {
        MedicamentoDTO dto = new MedicamentoDTO();
        dto.setId(medicamento.getId());
        dto.setNombre(medicamento.getNombre());
        // Agrega aquí otros campos según tu clase MedicamentoDTO
        return dto;
    }

    // Convertir DTO a entidad, asegurando la relación con la tabla pivote
    private HistorialRecordatorio convertToEntity(HistorialRecordatorioDTO dto) {
        HistorialRecordatorio historial = new HistorialRecordatorio();
        historial.setId(dto.getId());
        historial.setFechaEnvio(dto.getFechaEnvio());
        historial.setConfirmado(dto.isConfirmado());
        return historial;
    }
}