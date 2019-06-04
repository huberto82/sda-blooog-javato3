package servlet.user;

import servlet.HttpServletMethodProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetLoginUserAction implements HttpServletMethodProcessor {
    @Override
    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("user_login.jsp").forward(httpServletRequest,httpServletResponse);
    }
}
