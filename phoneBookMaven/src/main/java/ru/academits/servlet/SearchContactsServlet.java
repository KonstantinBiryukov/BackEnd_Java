package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.coverter.ContactConverter;
import ru.academits.model.Contact;
import ru.academits.service.ContactService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.List;

public class SearchContactsServlet extends HttpServlet {
    private static final long serialVersionUID = -5240899189321223452L;
    private ContactService phoneBookService = PhoneBook.phoneBookService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // Browser sends the url encoded, in format %22queryString%22 (for Chrome),
            // decoding is applied to turn %22 into a quote
            String queryString = URLDecoder.decode(req.getQueryString(), "UTF-8");
            // trim quotes from queryString and turn query's letters into UpperCase
            // "queryString" --> QUERYSTRING
            String clearQuery = queryString.substring(1, queryString.length() - 1).toUpperCase();
            List<Contact> contactList = phoneBookService.filterContacts(clearQuery);

            String contactListJson = contactConverter.convertToJson(contactList);
            resp.getOutputStream().write(contactListJson.getBytes(Charset.forName("UTF-8")));
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        } catch (Exception e) {
            System.out.println("error in GetAllContactsServlet GET: ");
            e.printStackTrace();
        }
    }
}