package com.github.maximovj.libhubtec.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.github.maximovj.libhubtec.dao.IAccountDao;
import com.github.maximovj.libhubtec.dao.IRecoverAccountDao;
import com.github.maximovj.libhubtec.model.Account;
import com.github.maximovj.libhubtec.model.RecoverAccount;
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
    private final IRecoverAccountDao recoverAccountDao;
    private final IAccountDao accountDao;

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

            Map<String, Object> claims = new HashMap<>();
            String name = userInfo.getLast_name() + " " + userInfo.getName();
            String code = String.valueOf(this.generateRandomFiveDigitNumber());
            claims.put("code", code);
            String token = this.jwtService.generateTokenWithClaimns(claims, userInfo.getId());

            // Crear el contexto para Thymeleaf
            Context context = new Context();
            context.setVariable("code", code);
            context.setVariable("token", token);
            context.setVariable("name", name);

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
            this.registerRecoverAccount(userInfo, token, code);
        } catch (Exception e) {
            // TODO: handle exception
            log.info(e.getMessage());
        }
    }

    private int generateRandomFiveDigitNumber() 
    {
        return ThreadLocalRandom.current().nextInt(10000, 100000); // Rango [10000, 99999]
    }

    private void registerRecoverAccount(UserInfo userInfo, String token, String code)
    {
        Account account = this.accountDao.findById(userInfo.getId()).get();
        RecoverAccount recoverAccount = new RecoverAccount();
        recoverAccount.setAccount(account);
        recoverAccount.setEmail(account.getEmail());
        recoverAccount.setLast_name(account.getLast_name());
        recoverAccount.setName(account.getName());
        recoverAccount.setToken(token);
        recoverAccount.setCode(Integer.parseInt(code));
        recoverAccount.setActive(true);

        Optional<RecoverAccount> findRecoverAccount = this.recoverAccountDao.findByAccountId(account.getId());
        
        if(!findRecoverAccount.isEmpty()) {
            this.recoverAccountDao.delete(findRecoverAccount.get());
        }
        
        this.recoverAccountDao.save(recoverAccount);

    }

}
