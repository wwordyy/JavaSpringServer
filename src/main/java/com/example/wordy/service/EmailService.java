package com.example.wordy.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage, false, "UTF-8");

        helper.setTo(to);

        helper.setSubject(subject);

        helper.setText(text, false);

        helper.setFrom("bragooff@gmail.com");

        mailSender.send(mimeMessage);


    }

}
