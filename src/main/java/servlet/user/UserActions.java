package servlet.user;

import entity.user.NewUser;
import helper.Encoding;
import helper.user.UserRegistration;
import repository.Repositories;
import servlet.HttpServletMethodProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

final public class UserActions {
    public static final String PARAMETER_ACTION = "action";
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_TOKEN = "token";
    public static final String ATTRIBUTE_LOGGED_USER = "loggedUser";
    public static final String PARAMETER_NICK = "nick";
    public static final String PARAMETER_REPEATED_PASSWORD = "repeatedPassword";

    public enum GET{
        REGISTER(new GetRegisterUserAction()),
        VERIFY (new GetVerifyUserAction()),
        LOGIN(new GetLoginUserAction()),
        VIEW_ALL(new GetViewAllUserAction()),
        VIEW(new GetViewUserAction());

        private HttpServletMethodProcessor processor;

        GET(HttpServletMethodProcessor processor){
            this.processor = processor;
        }

        public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            processor.process(req, resp);
        }
    }
    public enum POST {
        REGISTER(new PostRegisterUserAction()),
        POST_LOGIN(new PostLoginUserAction());

        private HttpServletMethodProcessor processor;

        POST(HttpServletMethodProcessor processor) {
            this.processor = processor;
        }

        public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            processor.process(req, resp);
        }
    }

    private UserActions(){
    }
}
