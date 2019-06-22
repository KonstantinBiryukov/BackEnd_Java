package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.model.Contact;
import ru.academits.service.ContactService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

public class StartServlet extends HttpServlet {
    private static final long serialVersionUID = -5546674637156297202L;
    private ContactService contactService = PhoneBook.phoneBookService;
    private ContactService phoneBookService = PhoneBook.phoneBookService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get QueryString: if it's empty - show all contacts,
        // if there's some query string, it means that user has made a contacts search ->
        // we need to search contacts using user's query and load filtered contacts
        String[] reqValues = req.getParameterValues("queryString");
        if (reqValues != null) {
            String query = reqValues[0];
            // Browser sends the url encoded, in format %22queryString%22 (for Chrome),
            // decoding is applied to turn %22 into a quote
            String queryString = URLDecoder.decode(query, "UTF-8").toUpperCase();
            List<Contact> contactList = phoneBookService.filterContacts(queryString);
            req.setAttribute("contactList", contactList);
        } else {
            req.setAttribute("contactList", contactService.getAllContacts());
        }
        req.setAttribute("contactValidation", contactService.getLastContactValidation());
        req.setAttribute("currentContact", contactService.getLastContact());
        req.getRequestDispatcher("phonebook.jsp").forward(req, resp);
    }
}