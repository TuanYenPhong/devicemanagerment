package com.deviceomi.service.impl;

import com.deviceomi.model.PasswordReset;
import com.deviceomi.service.EmailService;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@PropertySource("classpath:email.properties")
@Service
public class EmailServiceImpl implements EmailService {
    @Value("${mail.username}")
    private String EMAIL;

    @Value("${mail.password}")
    private String password;

    @Value("${website}")
    private String page;

    @Value("${send.mail.title}")
    private String title;

    @Value("${send.mail.div.start}")
    private String start;

    @Value("${send.mail.div.end}")
    private String end;


//    public Properties Properties(){
//        Properties properties=new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//        return properties;
//    }
//
//    @Override
//    public boolean sendmail(PasswordReset passwordReset) throws MessagingException{
//        Properties properties=Properties();
//
//        StringBuilder linkResetPassword=new StringBuilder(page);
//        linkResetPassword.append(passwordReset.getToken());
//
//        if(passwordReset==null || userRepository.existsByUserName(passwordReset.getUser().getUserName())){
//            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(EMAIL, password);
//                }
//            });
//            Transport.send(send(session,passwordReset.getUser().getUserName(), linkResetPassword.toString()));
//            return true;
//        }
//        return false;
//    }
//
//    public Message send(Session session,String mail,String link) throws MessagingException {
//        Message msg = new MimeMessage(session);
//        msg.setFrom(new InternetAddress(EMAIL, false));
//
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
//        msg.setSubject(title);
//        msg.setContent(title, "text/html");
//        msg.setSentDate(new Date());
//
//        MimeBodyPart messageBodyPart = new MimeBodyPart();
//        messageBodyPart.setContent(context, "text/html");
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//
//        attachPart.setText(link);
//        multipart.addBodyPart(attachPart);
//        msg.setContent(multipart);
//        return msg;
//    }

    @Override
    public boolean sendMail(PasswordReset passwordReset){
        if(passwordReset!=null) {
            try {
                String contextResetPassword = new StringBuilder(start).append(page).append(passwordReset.getToken()).append(end).toString();

                Email email = emailConfiguration();                             //Tạo đối tượng Email và cấu hình
                email.setFrom(EMAIL);                                           // Người gửi
                email.addTo(passwordReset.getUser().getUserName());             // Người nhận
                email.setSubject(title);                                        // Tiêu đề
                email.setContent(contextResetPassword,"text/html");  // Nội dung email
                email.send();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Email emailConfiguration(){
        Email email = new SimpleEmail();

        // Cấu hình thông tin Email Server
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(EMAIL,password));       //User name và password của email dùng để send
        email.setSSLOnConnect(true);        // Với gmail cái này là bắt buộc.
        return email;
    }
}
