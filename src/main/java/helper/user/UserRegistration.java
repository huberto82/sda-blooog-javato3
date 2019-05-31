package helper.user;

import config.BlooogConfig;
import entity.user.User;
import helper.Encoding;
import helper.MailService;

public class UserRegistration{

    private static final String REGISTER_ACTION = "/user?action=verify";

    public static boolean verifyUser(User user, String token){
        return Encoding.generateToken(getUserToken(user)).equals(token);
    }

    public static void sendRegistrationMail(User user) {
        MailService mailService = new MailService(BlooogConfig.VALUES.getMailUser(), BlooogConfig.VALUES.getMailPassword());

        mailService.sendMailTo(user.email,
                "Bloog: verification link for registration",
                "Kliknij w link  "
                        + BlooogConfig.VALUES.getUrl()
                        + REGISTER_ACTION
                        + "&id="
                        + user.id
                        + "&token="
                        + Encoding.generateToken(getUserToken(user)));
    }
    private static String getUserToken(User user){
        return user.nick+ user.password + user.email + user.registered;
    }
}
