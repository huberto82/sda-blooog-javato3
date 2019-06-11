package helper.user;

import config.Config;
import entity.user.User;
import helper.Encoding;
import helper.MailService;
import servlet.user.UserActions;

public class UserRegistration{
    private static final String REGISTER_ACTION = "/user?"+ UserActions.PARAMETER_ACTION+"="+UserActions.GET.VERIFY;

    public static boolean verifyUser(User user, String token){
        return Encoding.generateToken(getUserToken(user)).equals(token);
    }

    public static void sendRegistrationMail(User user) {
        MailService mailService = new MailService(Config.Mail.USER.toString(), Config.Mail.PASSWORD.toString());
        mailService.sendMailTo(user.email,
                "Bloog: verification link for registration",
                "Kliknij w link  "
                        + Config.Server.URL
                        + REGISTER_ACTION
                        + "&"+UserActions.PARAMETER_ID+"="
                        + user.id
                        + "&"+UserActions.PARAMETER_TOKEN+"="
                        + Encoding.generateToken(getUserToken(user)));
    }

    private static String getUserToken(User user){
        return user.nick+ user.password + user.email + user.registered;
    }
}
