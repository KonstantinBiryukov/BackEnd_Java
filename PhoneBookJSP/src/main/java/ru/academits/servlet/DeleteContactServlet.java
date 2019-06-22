package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.service.ContactService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteContactServlet extends HttpServlet {
    private static final long serialVersionUID = -4554423888294460746L;
    private ContactService contactService = PhoneBook.phoneBookService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String[] reqValues = req.getParameterValues("contactId");
            int contactId = Integer.parseInt((reqValues[0]));
            contactService.deleteContact(contactId);
        } catch (Exception e) {
            System.out.println("error in DeleteAllContactsServlet");
        }
        resp.sendRedirect("/phonebook");
    }
}