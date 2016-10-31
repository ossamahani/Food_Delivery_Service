package com.ea.aop;

import com.ea.aop.email.SmtpGmailSender;
import com.ea.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by amanadhikari on 9/25/16.
 */
@Aspect
@Component
public class MailAdvice {
    @Autowired
    private SmtpGmailSender smtpGmailSender;
    @After("execution(* com.ea.service.implementation.UserServiceImpl.save(..))&& args(user)")
    public void printSavedUser(JoinPoint joinPoint, User user)throws Throwable{
        printMessage("Thank you!!!");
        /*System.out.println("user = " + user);
        String successMsg = "You have successfully signed up in to the system. Please confirm your email by click on following link "+"http://localhost:8080/validateUser/"+user.getUsername();
        smtpGmailSender.newSend(user.getEmail(), "Sign Up Successful", successMsg);
        printMessage("User successfully signed up and email already sent");
        printMessage("success_msg");*/
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}
