package com.sena.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sena.hospital.service.emailService;

@RestController
public class emailController {

    @Autowired
    private emailService emailService;

    // Envío de correo para nueva cuenta
    // localhost:8080/AccountEmail/{email}
    @GetMapping("/AccountEmail/{email}")
    public String sendNewAccountEmail(@PathVariable String email) {
        emailService.sendNewAccountEmail(email);
        return "Correo de nueva cuenta enviado a " + email;
    }

    // Envío de correo para recuperación de contraseña
    // localhost:8080/forgotPasswordEmail/{email}
    @GetMapping("/forgotPasswordEmail/{email}")
    public String sendForgotPasswordEmail(@PathVariable String email) {
        emailService.sendForgotPasswordEmail(email);
        return "Correo de recuperación de contraseña enviado a " + email;
    }

    // Envío de correo para activación de cuenta
    // localhost:8080/activationEmail/{email}
    @GetMapping("/activationEmail/{email}")
    public String sendActivationEmail(@PathVariable String email) {
        emailService.sendActivationEmail(email);
        return "Correo de activación enviado a " + email;
    }    

    // Envío de notificación de cambio de contraseña
    // localhost:8080/passwordChangeNotification/{email}
    @GetMapping("/passwordChangeNotification/{email}")
    public String sendPasswordChangeNotification(@PathVariable String email) {
        emailService.sendPasswordChangeNotification(email);
        return "Notificación de cambio de contraseña enviada a " + email;
    }

    // Envío de notificación de stock bajo
    // localhost:8080/lowStockNotification/{email}
    @GetMapping("/lowStockNotification/{email}")
    public String sendLowStockNotification(@PathVariable String email) {
        emailService.sendLowStockNotification(email);
        return "Notificación de stock bajo enviada a " + email;
    }    

    // Envío de notificación de compra
    // localhost:8080/purchaseNotification/{email}
    @GetMapping("/purchaseNotification/{email}")
    public String sendPurchaseNotification(@PathVariable String email) {
        emailService.sendPurchaseNotification(email);
        return "Notificación de compra enviada a " + email;
    }
}