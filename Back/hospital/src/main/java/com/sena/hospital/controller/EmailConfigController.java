package com.sena.hospital.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sena.hospital.DTO.PacienteDTO;
import com.sena.hospital.config.emailConfig;
import com.sena.hospital.model.Paciente;
import com.sena.hospital.service.EmailSchedulerService;
import com.sena.hospital.service.PacienteService;

public class EmailConfigController {
    
    private final emailConfig emailConfig;
    private final EmailSchedulerService emailSchedulerService;
    private final PacienteService pacienteService;

    public EmailConfigController(emailConfig emailConfig, EmailSchedulerService emailSchedulerService, PacienteService pacienteService) {
        this.emailConfig = emailConfig;
        this.emailSchedulerService = emailSchedulerService;
        this.pacienteService = pacienteService;
    }

    @GetMapping("/email-status")
    public ResponseEntity<Map<String, Boolean>> getEmailStatus() {
        return ResponseEntity.ok(Collections.singletonMap("enabled", emailConfig.isEnabled()));
    }

    @PostMapping("/toggle-email")
    public ResponseEntity<Map<String, String>> toggleEmail(@RequestBody Map<String, Boolean> request) {
        boolean enabled = request.getOrDefault("enabled", false);
        emailConfig.setEnabled(enabled);

        return ResponseEntity.ok(Collections.singletonMap("message",
            "Correos electrónicos " + (enabled ? "activados" : "desactivados")));
    }

    @GetMapping("/test-email/{pacienteId}")
    public ResponseEntity<String> testEmail(@PathVariable Long pacienteId) {
        PacienteDTO pacienteDto = pacienteService.obtenerPorId(pacienteId);
        if (pacienteDto != null) {
            // Convert PacienteDTO to Paciente
            Paciente paciente = new Paciente();
            paciente.setId(pacienteDto.getId());
            paciente.setNombre(pacienteDto.getNombre());
            paciente.setEmail(pacienteDto.getEmail());
            // Add other fields as necessary

            emailSchedulerService.sendTestEmail(paciente);
            return ResponseEntity.ok("Correo enviado correctamente a " + pacienteDto.getEmail());
        }
        return ResponseEntity.status(404).body("Paciente no encontrado");
    }
}