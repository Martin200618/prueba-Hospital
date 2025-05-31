package com.sena.hospital.controller;

import com.sena.hospital.DTO.MedicamentoDTO;
import com.sena.hospital.DTO.ResponseDTO;
import com.sena.hospital.service.MedicamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "*")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoDTO>> obtenerTodos() {
        List<MedicamentoDTO> medicamentos = medicamentoService.obtenerTodos();
        return ResponseEntity.ok(medicamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicamentoById(@PathVariable Long id) {
        MedicamentoDTO medicamento = medicamentoService.obtenerPorId(id);
        if (medicamento != null) {
            return ResponseEntity.ok(medicamento);
        } else {
            return ResponseEntity.status(404).body(new ResponseDTO("404 NOT FOUND", "Medicamento no encontrado"));
        }
    }

    @PostMapping
    public ResponseEntity<MedicamentoDTO> createMedicamento(@RequestBody MedicamentoDTO medicamentoDTO) {
        MedicamentoDTO response = medicamentoService.guardarMedicamento(medicamentoDTO);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicamento(@PathVariable Long id) {
        medicamentoService.eliminarMedicamento(id);
        return ResponseEntity.noContent().build();
    }
}