package helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailServiceTest {

    @Test
    void sendMailTo() {
        MailService service = new MailService("blooog@record-it.pl","B1000g");
        service.sendMailTo("siwon.cezary@gmail.com","blooog registration","Hello user");
    }
}