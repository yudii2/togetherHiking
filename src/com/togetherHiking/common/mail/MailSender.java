package com.togetherHiking.common.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import com.togetherHiking.common.code.Config;
import com.togetherHiking.common.code.ErrorCode;
import com.togetherHiking.common.exception.HandleableException;

public class MailSender {

   private static final Properties SMTP_PROPERTIES;
   
   //변경되지 않는 코드 => static
   static {
      SMTP_PROPERTIES = new Properties();
      SMTP_PROPERTIES.put("mail.smtp.host", "smtp.gmail.com");
      SMTP_PROPERTIES.put("mail.smtp.starttls.enable", "true");
      SMTP_PROPERTIES.put("mail.smtp.starttls.required", "true");
      SMTP_PROPERTIES.put("mail.smtp.port", "587");
      SMTP_PROPERTIES.put("mail.smtp.auth", "true");
      SMTP_PROPERTIES.put("mail.smtp.ssl.protocols", "TLSv1.2");
   }
   
   
   public void sendEmail(String to, String subject, String htmlText) {
       

       try {
           MimeMessage msg = new MimeMessage(getSession());
           msg.setFrom(Config.COMPANY_EMAIL.DESC);
          msg.setRecipients(Message.RecipientType.TO,to);
          msg.setSubject(subject);
          msg.setSentDate(new Date());
          msg.setText(htmlText,"utf-8","html");
          
          Transport.send(msg);
      
       } catch (MessagingException mex) {
          throw new HandleableException(ErrorCode.MAIL_SEND_FAIL_ERROR, mex);
      }
      
   
   }
   
   private Session getSession() {
      Session session = Session.getInstance(SMTP_PROPERTIES, new Authenticator(){
          
          public PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication(Config.SMTP_AUTHENTICATION_ID.DESC
                                        ,Config.SMTP_AUTHENTICATION_PASSWORD.DESC);
          }
          
       
      });
      return session;
   }
   
   
   
   
   
   
}