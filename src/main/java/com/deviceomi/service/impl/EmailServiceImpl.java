package com.deviceomi.service.impl;

import com.deviceomi.model.PasswordReset;
import com.deviceomi.repository.UserRepository;
import com.deviceomi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${mail.username}")
    private String EMAIL;

    @Value("${mail.password}")
    private String password;

    @Autowired
    private UserRepository userRepository;

    @Value("${website}")
    private String page;

    public Properties Properties(){
        Properties properties=new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return properties;
    }

    @Override
    public boolean sendmail(PasswordReset passwordReset) throws MessagingException{
        Properties properties=Properties();

        StringBuilder linkResetPassword=new StringBuilder(page);
        linkResetPassword.append(passwordReset.getToken());

        if(passwordReset==null || userRepository.existsByUserName(passwordReset.getUser().getUserName())){
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL, password);
                }
            });
            Transport.send(send(session,passwordReset.getUser().getUserName(), linkResetPassword.toString()));
            return true;
        }
        return false;
    }

    public Message send(Session session,String mail,String link) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(EMAIL, false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
        msg.setSubject("Reset password");
        msg.setContent("Reset password", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("if you want to reset your password, click on the link below :", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        attachPart.setText(link);
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        return msg;
    }
}
