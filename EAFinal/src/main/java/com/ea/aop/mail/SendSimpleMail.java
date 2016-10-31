package com.ea.aop.mail;

import javax.mail.MessagingException;

/**
 * Created by amanadhikari on 9/27/16.
 */
public class SendSimpleMail {
    public static void main(String[] args) throws MessagingException {
        /*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
        mailMsg.setFrom("adhikariaman01@gmail.com");
        mailMsg.setTo("adhikariaman01@gmail.com");
        mailMsg.setSubject("Test mail");
        mailMsg.setText("Hello World!");
        mailSender.send(mimeMessage);
        System.out.println("---Done---");*/
    }
}
