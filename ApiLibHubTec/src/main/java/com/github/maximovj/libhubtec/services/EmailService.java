package com.github.maximovj.libhubtec.services;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.github.maximovj.libhubtec.user.UserInfo;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {


    private final JwtService jwtService;
    private final Environment environment;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sendEmail(String to, String subject, String text)
    {
        String emailSupport = this.environment.getProperty("app.email.support");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(emailSupport);
        this.mailSender.send(message);
    }

    public void sendEmailRecoverPassword(UserInfo userInfo) 
    {
        try {
            // Obtener correo electrónico de soporte
            String emailSupport = this.environment.getProperty("app.email.support");

            String token = this.jwtService.generateToken(userInfo.getId());
            String code = String.valueOf(this.generateRandomFiveDigitNumber());

            // Crear el contexto para Thymeleaf
            Context context = new Context();
            context.setVariable("code", code);
            context.setVariable("token", this.jwtService.hiddenToken(token));
            context.setVariable("name", userInfo.getLast_name() + " " + userInfo.getName());

            // Procesar la plantilla HTML
            String htmlContent = this.templateEngine.process("email", context);

            // Crear el mensaje de correo
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setTo(userInfo.getEmail());
            helper.setSubject("Recuperar cuenta de LibHubTec");
            helper.setText(htmlContent, true);
            helper.setFrom(emailSupport);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            // TODO: handle exception
            log.info(e.getMessage());
        }
    }

    private int generateRandomFiveDigitNumber() {
        return ThreadLocalRandom.current().nextInt(10000, 100000); // Rango [10000, 99999]
    }

}
