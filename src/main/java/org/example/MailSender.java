package org.example;

import com.sun.mail.smtp.SMTPTransport;


import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class MailSender {

    private final String smpt_server = "smpt_server";
    private final String username = "username";
    private final String password = "password";
    private final String receiver = "receiver";
    private final Properties properties;
    public MailSender() {
        properties  = System.getProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", smpt_server);
        properties.put("mail.smtp.user", username);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", smpt_server);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
    }

    public void sendMail(String subject, String htmlText) {
        try {
            Session session = Session.getInstance(properties, null);
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
            t.connect(smpt_server, username, password);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            MimeBodyPart htmlPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiver, false));
            msg.setSubject(subject);
            htmlPart.setContent(htmlText, "text/html; charset=utf-8");
            multipart.addBodyPart(htmlPart);
            // send
            msg.setContent(multipart);
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
            Thread.sleep(2000);
        } catch (MessagingException | InterruptedException e) {
            System.err.println("sendMail by devs " + e.getMessage());
        }
    }
}