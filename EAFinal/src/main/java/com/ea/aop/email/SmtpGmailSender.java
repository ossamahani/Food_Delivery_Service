package com.ea.aop.email;

import com.ea.aop.mail.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * Created by amanadhikari on 9/25/16.
 */
@Component
public class SmtpGmailSender{
    @Autowired
    private JavaMailSender javaMailSender;

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public boolean send(String mail_to, String subject, String msg) throws MessagingException{
        boolean status = true;
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper;

            helper = new MimeMessageHelper(message, true);

            helper.setSubject(subject);
            helper.setTo(mail_to);
            helper.setText(msg, true);

            javaMailSender.send(message);
        }catch (MailException mailException){
            System.out.println("An error occured while sending an email to "+mail_to);
            System.out.println("Email Exception :: " + mailException);
            status = false;
        }
        return status;
    }

    @Async
    public boolean newSend(String mail_to, String subject, String msg) throws MessagingException {
        boolean status = true;
        try{
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
            ctx.register(AppConfig.class);
            ctx.refresh();
            JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
            mailMsg.setFrom("adhikariaman01@gmail.com");
            mailMsg.setTo(mail_to);
            mailMsg.setSubject(subject);
            mailMsg.setText(msg);
            mailSender.send(mimeMessage);
        }catch (MailException mailException){
            System.out.println("An error occured while sending an email to "+mail_to);
            System.out.println("Email Exception :: " + mailException);
            status = false;
        }
        return status;
    }
}
