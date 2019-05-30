package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.coverter.ContactConverter;
import ru.academits.model.Contact;
import ru.academits.service.ContactService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;

public class SearchContactsServlet extends HttpServlet {
    private static final long serialVersionUID = -6616095261016617030L;
    private ContactService phoneBookService = PhoneBook.phoneBookService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            System.out.println(req);
            List<Contact> contactList = phoneBookService.getAllContacts();
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