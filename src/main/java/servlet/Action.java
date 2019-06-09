package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {

    public HttpServletMethodProcessor getProcessor();

    default public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getProcessor().process(req, resp);
    }
}
