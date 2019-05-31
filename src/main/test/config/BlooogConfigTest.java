package config;

import static org.junit.jupiter.api.Assertions.*;

class BlooogConfigTest {

    @org.junit.jupiter.api.Test
    void getMailPop3() {
        assertEquals("mail.record-it.pl",BlooogConfig.VALUES.getMailPop3());
    }

    @org.junit.jupiter.api.Test
    void getMailSmtp() {
        assertEquals("mail.record-it.pl",BlooogConfig.VALUES.getMailSmtp());
    }

    @org.junit.jupiter.api.Test
    void getMailUser() {
        assertEquals("root",BlooogConfig.VALUES.getMailUser());
    }

    @org.junit.jupiter.api.Test
    void getMailPassword() {
        assertEquals("",BlooogConfig.VALUES.getMailPassword());
    }

    @org.junit.jupiter.api.Test
    void getErrorMessage() {
        assertEquals("No errors",BlooogConfig.VALUES.getErrorMessage());
    }

    @org.junit.jupiter.api.Test
    void getUrl() {
        assertEquals("http://localhost:8080/blooog_war",BlooogConfig.VALUES.getUrl());
    }
}