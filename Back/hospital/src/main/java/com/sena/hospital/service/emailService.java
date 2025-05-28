package com.sena.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class emailService {
    @Autowired
    private JavaMailSender javaMailSender;

    // Método para enviar un correo según el tipo
    public boolean sendEmail(String addressMail, String subject, String bodyMail) throws MessagingException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(addressMail);
            helper.setSubject(subject);
            helper.setText(bodyMail, true);
            javaMailSender.send(message);
            return true;
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // Plantilla para Cuenta Nueva
    public void sendNewAccountEmail(String addressMail) {
        String subject = "¡Bienvenido a nuestra plataforma!";
        String bodyMail = 
        "<html>" +
        "<body style='font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px;'>" +
        "<div style='background-color: #ffffff; border: 1px solid #ddd; padding: 20px; border-radius: 10px;'>" +
        "<h1 style='color: #4CAF50;'>¡Cuenta nueva creada!</h1>" +
        "<p style='color: #333;'>Hola, tu cuenta ha sido creada con éxito. Gracias por unirte a nosotros.</p>" +
        "</div>" +
        "</body>" +
        "</html>";
        try {
            sendEmail(addressMail, subject, bodyMail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Plantilla para Olvidé mi contraseña
    public void sendForgotPasswordEmail(String addressMail) {
        String subject = "Solicitud para restablecer contraseña";
        String bodyMail = 
        "<html>" +
        "<body style='font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px;'>" +
        "<div style='background-color: #ffffff; border: 1px solid #ddd; padding: 20px; border-radius: 10px;'>" +
        "<h1 style='color: #2196F3;'>Restablecimiento de contraseña</h1>" +
        "<p style='color: #333;'>Haz clic en el siguiente enlace para restablecer tu contraseña:</p>" +
        "<a href='https://example.com/reset-password' style='color: #FF5722;'>Restablecer Contraseña</a>" +
        "</div>" +
        "</body>" +
        "</html>";
        try {
            sendEmail(addressMail, subject, bodyMail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Plantilla para Correo de activación
    public void sendActivationEmail(String addressMail) {
        String subject = "Activación de cuenta";

        // Código de activación quemado
        String activationCode = "123456";

        String bodyMail =
            "<html>" +
            "<body style='font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px;'>" +
            "<div style='background-color: #ffffff; border: 1px solid #ddd; padding: 20px; border-radius: 10px;'>" +
            "<h1 style='color: #4CAF50;'>¡Activa tu cuenta!</h1>" +
            "<p style='color: #333;'>Usa el siguiente código para activar tu cuenta:</p>" +
            "<h2 style='color: #FF5722;'>" + activationCode + "</h2>" +
            "<p style='color: #333;'>Si no solicitaste esta activación, por favor ignora este correo.</p>" +
            "</div>" +
            "</body>" +
            "</html>";

        try {
            sendEmail(addressMail, subject, bodyMail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Plantilla para Notificación de cambio de contraseña
    public void sendPasswordChangeNotification(String addressMail) {
        String subject = "Cambio de contraseña exitoso";
        String bodyMail = 
        "<html>" +
        "<body style='font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px;'>" +
        "<div style='background-color: #ffffff; border: 1px solid #ddd; padding: 20px; border-radius: 10px;'>" +
        "<h1 style='color: #4CAF50;'>Tu contraseña ha sido actualizada</h1>" +
        "<p style='color: #333;'>Si no realizaste este cambio, por favor contacta a soporte de inmediato.</p>" +
        "</div>" +
        "</body>" +
        "</html>";
        try {
            sendEmail(addressMail, subject, bodyMail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Plantilla para Notificación de stock bajo con productos quemados
    public void sendLowStockNotification(String addressMail) {
        String subject = "¡Stock bajo en productos!";

        // Productos con stock bajo quemados directamente
        String productStockList = """
            <ul>
                <li>Producto: Laptop - Stock disponible: 3</li>
                <li>Producto: Monitor 24 pulgadas - Stock disponible: 2</li>
                <li>Producto: Mouse inalámbrico - Stock disponible: 5</li>
            </ul>
        """;

        String bodyMail = 
            "<html>" +
            "<body style='font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px;'>" +
            "<div style='background-color: #ffffff; border: 1px solid #ddd; padding: 20px; border-radius: 10px;'>" +
            "<h1 style='color: #FF5722;'>Notificación de Stock Bajo</h1>" +
            "<p style='color: #333;'>Los siguientes productos tienen niveles de stock bajos:</p>" +
            productStockList +
            "</div>" +
            "</body>" +
            "</html>";

        try {
            sendEmail(addressMail, subject, bodyMail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Plantilla para Notificación de compra con lista de productos
    public void sendPurchaseNotification(String addressMail) {
        String subject = "Confirmación de compra";

        // Lista de productos quemada (hardcoded)
        String productList = """
            <li>Producto 1: Laptop</li>
            <li>Producto 2: Mouse inalámbrico</li>
            <li>Producto 3: Teclado mecánico</li>
            <li>Producto 4: Monitor 24 pulgadas</li>
        """;

        String bodyMail =
            "<html>" +
            "<body style='font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px;'>" +
            "<div style='background-color: #ffffff; border: 1px solid #ddd; padding: 20px; border-radius: 10px;'>" +
            "<h1 style='color: #4CAF50;'>Gracias por tu compra</h1>" +
            "<p style='color: #333;'>Los productos que adquiriste son:</p>" +
            "<ul style='color: #333;'>" + productList + "</ul>" +
            "</div>" +
            "</body>" +
            "</html>";

        try {
            sendEmail(addressMail, subject, bodyMail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}