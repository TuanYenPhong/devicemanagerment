package com.deviceomi.service;

import com.deviceomi.model.PasswordReset;

import javax.mail.MessagingException;

public interface EmailService {
//    boolean sendmail(PasswordReset passwordReset) throws MessagingException;
    boolean sendMail(PasswordReset passwordReset);
}
