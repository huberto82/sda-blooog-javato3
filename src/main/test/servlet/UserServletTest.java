package servlet;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class UserServletTest extends Mockito{

    @Test
    void doGetLoginAction() throws ServletException, IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("action")).thenReturn("ADD_USER");
        when(request.getParameter("user")).thenReturn("me");
        when(request.getParameter("password")).thenReturn("secret");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);

        new UserServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("user"); // only if you want to verify username was called...

        writer.flush(); // it may not have been flushed yet...

        assertTrue(stringWriter.toString().contains("My expected string"));
    }
}