package helper.user;

import config.BlooogConfig;
import entity.user.User;
import helper.Encoding;
import helper.MailService;

import java.security.NoSuchAlgorithmException;

public class UserRegistration{

    static boolean verifyRegistrationToken(User user, String token){
        return Encoding.generateToken(user.nick+ user.password + user.email).equals(token);
    }

    static void sendRegistrationMail(User user) throws NoSuchAlgorithmException {
        MailService mailService = new MailService(BlooogConfig.VALUES.getMailUser(), BlooogConfig.VALUES.getMailPassword());
        mailService.sendMailTo(user.email,
                "Bloog: verification link for registration",
                "Kliknij w link  "+ Encoding.generateToken(user.nick+ user.password + user.email));
    }
}
