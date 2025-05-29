package com.sena.hospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.hospital.DTO.patientDTO;
import com.sena.hospital.model.patient;
import com.sena.hospital.repository.Ipatient;

@Service
public class patientService {
    @Autowired
    private Ipatient patientRepository;

    //Obtener todos los pacientes
    public List<patient> findAll() {
        try{
            return patientRepository.findAll();
        } catch (Exception e){
            throw new RuntimeException("Error al obtener los pacientes: "+ e.getMessage());
        }
    }

    //Obtener un paciente
    public Optional<patient> findById(int patientId){
        try{
            return patientRepository.findById(patientId);
        }catch(Exception e){
            throw new RuntimeException("Error al obtener el paciente con ID: "+ patientId+ ", error: "+ e.getMessage());
        }
    }

    //registrar un paciente
    public String save(patientDTO patientDTO){
        if(patientDTO == null || !isValidPatient(patientDTO)){
            return HttpStatus.BAD_REQUEST.toString() + ": Los datos del paciente son invalidos";
        }

        try{
            patient patient = converToModel(patientDTO);
            patientRepository.save(patient);
            return HttpStatus.OK.toString() + ": Paciente registrado exitosamente";
        }catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR.toString()+ ": Error al registrar el paciente: " +e.getMessage();
        }
    }

    //Actualizar el paciente
    public String update(int patientId, patientDTO patientDTO){
        Optional<patient> existingpatient = findById(patientId);

        if(!existingpatient.isPresent()){
            return HttpStatus.NOT_FOUND.toString()+ ": El paciente del ID "+ patientId + " no existe o no se encuentra para su edicion";
        }

        try{
            patient patientToUpdate = existingpatient.get();
            patientToUpdate.setCorreo(patientDTO.getCorreo());
            patientToUpdate.setNombre(patientDTO.getNombre());
            patientToUpdate.setConfirmacion(patientDTO.getConfirmacion());
            return HttpStatus.OK.toString() + ": paciente actualizado correctamente";
        }catch(Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al actualizar el paciente: "+ e.getMessage();
        }
    }

    //Eliminar el paciente
    public String delete(int patientId){
        Optional<patient> patient = findById(patientId);

        if(!patient.isPresent()){
            return HttpStatus.NOT_FOUND.toString() + ": El paciente del Id "+ patientId + " no existe o ya a sido eliminado";
        }

        try{
            patientRepository.deleteById(patientId);
            return HttpStatus.OK.toString()+ ": Paciente eliminado correctamente";
        }catch(Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR.toString()+ ": Error al eliminar el artista: " + e.getMessage();
        }
    }

    //conversión de DTO a Entidad
    public patient converToModel(patientDTO patientDTO){
        return new patient(
            0, 
            patientDTO.getNombre(), 
            patientDTO.getCorreo(), 
            patientDTO.getConfirmacion()
        );
    }

    //Validacion de datos del paciente
    private boolean isValidPatient(patientDTO patientDTO){
        return patientDTO.getNombre() != null && !patientDTO.getNombre().trim().isEmpty() && patientDTO.getNombre().length() <= 100;
    }
}