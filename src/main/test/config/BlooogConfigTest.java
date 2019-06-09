package config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlooogConfigTest {

    @Test
    void getMailPop3() {
        System.out.println(Config.Mail.POP3);
        System.out.println(BlooogConfig.VALUES.getMailPop3());
        assertEquals(BlooogConfig.VALUES.getMailPop3(), Config.Mail.POP3);
    }

    @Test
    void getMailSmtp() {
        assertEquals(BlooogConfig.VALUES.getMailSmtp(), Config.Mail.SMTP);
    }

    @Test
    void getUser() {
        assertEquals(BlooogConfig.VALUES.getMailUser(), Config.Mail.USER);
    }
}