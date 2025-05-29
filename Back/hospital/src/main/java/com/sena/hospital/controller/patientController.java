package com.sena.hospital.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.hospital.DTO.patientDTO;
import com.sena.hospital.service.patientService;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"}) //Asegura solicitudes de ambos origenes
@RestController
@RequestMapping("api/v1/patient")
public class patientController {
    @Autowired
    private patientService patientService;

    //Registrar un nuevo artista
    @PostMapping("/")
    public ResponseEntity<Object> registerPatient(@RequestBody patientDTO patient){
        String response = patientService.save(patient);
        boolean isSuccessful = response.startsWith("200");

        //Respuesta estructurada
        return new ResponseEntity<>(
            Map.of(
                "message", response,
                "status", isSuccessful ? "success" : "error"
            ),isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    //Obtener todos los pacientes
    @GetMapping("/")
    public ResponseEntity<Object> getAllPatient(){
        try{
            return new ResponseEntity<>(
                patientService.findAll(),
                HttpStatus.OK
            );
        }catch(Exception e){
            return new ResponseEntity<>(
                Map.of("message", "Error al obtener los pacientes", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    //Obten un unico paciente
    @PostMapping("/update/{patientId}")
    public ResponseEntity<Object> updatePatient(@PathVariable("patientId") int patientId, @RequestBody patientDTO patient){
        try{
            String response = patientService.update(patientId, patient);
            return new ResponseEntity<>(
                Map.of(
                    "message", response,
                    "status", response.startsWith("200") ? "success" : "error"
                ),
                response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
            );
        } catch (Exception e){
            return new ResponseEntity<>(
                Map.of("message", "Error al actualizar el artista", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    //Eliminar un unico paciente
    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<Object> deletePatient(@PathVariable("patientId") int patientId){
        try{
            String response = patientService.delete(patientId);
            return new ResponseEntity<>(
                Map.of(
                    "message", response,
                    "status", response.startsWith("200") ? "success" : "error"
                ),
                response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
            );
        }catch(Exception e){
            return new ResponseEntity<>(
                Map.of("message", "Error al eliminar el paciente", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}