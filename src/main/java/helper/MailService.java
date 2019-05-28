package helper;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailService {
    private final Properties prop;
    private final String password;
    private final String username;

    public MailService(String password, String username) {
        this.password = password;
        this.username = username;
        this.prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "mail.record-it.pl");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "mail.record-it.pl");
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
            message.setFrom(new InternetAddress(""));
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

        } catch (MessagingException e) {

        }
    }
}
