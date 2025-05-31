package com.sena.hospital.service;

import com.sena.hospital.model.HistorialRecordatorio;
import com.sena.hospital.model.Paciente;
import com.sena.hospital.repository.HistorialRecordatorioRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmailSchedulerService {

    @Autowired
    private HistorialRecordatorioRepository historialRepository;

    @Autowired
    private JavaMailSender mailSender;

    // Enviar correos diariamente a las 8 AM
    @Scheduled(cron = "0 0 8 * * ?")
    public void sendMedicationReminders() {
        List<HistorialRecordatorio> recordatorios = historialRepository.findAll();

        for (HistorialRecordatorio recordatorio : recordatorios) {
            sendEmail(recordatorio);
        }
    }

    private void sendEmail(HistorialRecordatorio recordatorio) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setTo(recordatorio.getPacienteId().getEmail());
            helper.setSubject("Recordatorio de Medicación");
            helper.setText("Estimado/a " + recordatorio.getPacienteId().getNombre() + 
                           ", recuerde tomar su medicamento: " + recordatorio.getMedicamentoId().getNombre() + 
                           " a las " + recordatorio.getFechaEnvio() + ".");

            mailSender.send(message);
            System.out.println("Correo enviado a: " + recordatorio.getPacienteId().getEmail());
        } catch (Exception e) {
            System.err.println("Error al enviar correo: " + e.getMessage());
        }
    }

    public void sendTestEmail(Paciente paciente) {
    try {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        helper.setTo(paciente.getEmail());
        helper.setSubject("Prueba de Envío de Correo");
        helper.setText("¡Hola " + paciente.getNombre() + "! Este es un correo de prueba desde el sistema.");

        mailSender.send(message);
        System.out.println("Correo de prueba enviado a: " + paciente.getEmail());
    } catch (Exception e) {
        System.err.println("Error al enviar correo de prueba: " + e.getMessage());
    }
}
}
