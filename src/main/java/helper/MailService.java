package helper;

import config.Config;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailService {
    private final Properties prop;
    private final String password;
    private final String username;

    public MailService(String username, String password) {
        this.password = password;
        this.username = username;
        this.prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.host", Config.Mail.SMTP.toString());
        prop.put("mail.smtp.port", Config.Mail.SMTP_PORT.toString());
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", Config.Mail.SMTP.toString());

    }

    public void sendMailTo(String email, String subject, String content) {
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("blooog@record-it.pl"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(content, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (AddressException e) {
            System.err.println("Address unknown");
        } catch (MessagingException e) {
            System.err.println("Messaging exception " + e);
        }
    }
}
