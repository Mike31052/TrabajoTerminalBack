package com.escom.impuestos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String url) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Verificación de cuenta");
            message.setText("Por favor, verifica tu cuenta haciendo clic en el siguiente enlace: " + url);

            mailSender.send(message);
            logger.info("Correo de verificación enviado exitosamente a {}", to);
        } catch (MailException e) {
            logger.error("Error al enviar el correo a {}: {}", to, e.getMessage());
            throw new IllegalStateException("Error al enviar el correo de verificación", e);
        }
    }
}

