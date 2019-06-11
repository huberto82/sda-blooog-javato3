package config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlooogConfigTest {

    @Test
    void getMailPop3() {
        assertEquals("mail.record-it.pl", Config.Mail.POP3.toString());
    }

    @Test
    void getMailSmtp() {
        assertEquals("mail.record-it.pl", Config.Mail.SMTP.toString());
    }

    @Test
    void getUser() {
        assertEquals("blooog@record-it.pl", Config.Mail.USER.toString());
    }
}