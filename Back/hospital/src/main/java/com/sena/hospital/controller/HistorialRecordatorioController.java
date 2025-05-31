package com.sena.hospital.controller;

import com.sena.hospital.DTO.HistorialRecordatorioDTO;
import com.sena.hospital.DTO.ResponseDTO;
import com.sena.hospital.service.HistorialService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historial")
@CrossOrigin(origins = "*")
public class HistorialRecordatorioController {

    private final HistorialService historialService;

    public HistorialRecordatorioController(HistorialService historialService) {
        this.historialService = historialService;
    }

    // Obtener todos los historiales como DTOs
    @GetMapping
    public ResponseEntity<List<HistorialRecordatorioDTO>> getAllHistorial() {
        List<HistorialRecordatorioDTO> historiales = historialService.getAllHistorial();
        return ResponseEntity.ok(historiales);
    }

    // Obtener historial por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getHistorialById(@PathVariable Long id) {
        Optional<HistorialRecordatorioDTO> historialOpt = historialService.getHistorialById(id);
        if (historialOpt.isPresent()) {
            return ResponseEntity.ok(historialOpt.get());
        }
        return ResponseEntity.status(404).body(new ResponseDTO("404 NOT FOUND", "Historial no encontrado"));
    }

    // Crear historial
    @PostMapping
    public ResponseEntity<ResponseDTO> createHistorial(@RequestBody HistorialRecordatorioDTO historialDTO) {
        ResponseDTO response = historialService.createHistorial(historialDTO);
        return response.getStatus().equals("200 OK") ? ResponseEntity.ok(response) : ResponseEntity.status(400).body(response);
    }

    // Eliminar historial
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteHistorial(@PathVariable Long id) {
        ResponseDTO response = historialService.deleteHistorial(id);
        return response.getStatus().equals("200 OK") ? ResponseEntity.noContent().build() : ResponseEntity.status(404).body(response);
    }
}