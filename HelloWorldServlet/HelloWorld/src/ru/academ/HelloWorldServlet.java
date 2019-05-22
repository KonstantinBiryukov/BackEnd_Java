package ru.academ;

import java.io.*;
import java.nio.charset.Charset;
import javax.servlet.http.*;

public class HelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = -4626394749418123866L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getOutputStream().write("Hello from Hello_World_Servlet!".getBytes(Charset.forName("UTF-8")));
        resp.getOutputStream().flush();
        resp.getOutputStream().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String hello = req.getParameter("hello");
        resp.getOutputStream().write(hello.getBytes(Charset.forName("UTF-8")));
        resp.getOutputStream().flush();
        resp.getOutputStream().close();
    }
}