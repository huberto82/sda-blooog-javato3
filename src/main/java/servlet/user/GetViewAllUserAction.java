package servlet.user;

import repository.Repositories;
import servlet.HttpServletMethodProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetViewAllUserAction implements HttpServletMethodProcessor {
    @Override
    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setAttribute("users", Repositories.USER.getAll().asJava());
        httpServletRequest.getRequestDispatcher("view_users.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
