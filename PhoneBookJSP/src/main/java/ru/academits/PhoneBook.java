package ru.academits;

import ru.academits.coverter.ContactConverter;
import ru.academits.dao.ContactDao;
import ru.academits.service.ContactService;

public class PhoneBook {
    public static ContactDao contactDao = new ContactDao();

    public static ContactService phoneBookService = new ContactService();

    public static ContactConverter contactConverter = new ContactConverter();
}
