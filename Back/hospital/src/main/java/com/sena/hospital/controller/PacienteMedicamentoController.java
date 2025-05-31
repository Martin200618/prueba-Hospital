package com.sena.hospital.controller;

import com.sena.hospital.DTO.PacienteMedicamentoDTO;
import com.sena.hospital.DTO.ResponseDTO;
import com.sena.hospital.service.PacienteMedicamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paciente-medicamento")
@CrossOrigin(origins = "*")
public class PacienteMedicamentoController {

    private final PacienteMedicamentoService pacienteMedicamentoService;

    public PacienteMedicamentoController(PacienteMedicamentoService pacienteMedicamentoService) {
        this.pacienteMedicamentoService = pacienteMedicamentoService;
    }

    @GetMapping
    public ResponseEntity<List<PacienteMedicamentoDTO>> obtenerTodos() {
        List<PacienteMedicamentoDTO> lista = pacienteMedicamentoService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPacienteMedicamentoById(@PathVariable Long id) {
        PacienteMedicamentoDTO dto = pacienteMedicamentoService.obtenerPorId(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(404).body(new ResponseDTO("404 NOT FOUND", "Relación paciente-medicamento no encontrada"));
        }
    }

    @PostMapping
    public ResponseEntity<PacienteMedicamentoDTO> createPacienteMedicamento(@RequestBody PacienteMedicamentoDTO dto) {
        PacienteMedicamentoDTO response = pacienteMedicamentoService.guardarAsociacion(dto);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacienteMedicamento(@PathVariable Long id) {
        pacienteMedicamentoService.eliminarAsociacion(id);
        return ResponseEntity.noContent().build();
    }
}