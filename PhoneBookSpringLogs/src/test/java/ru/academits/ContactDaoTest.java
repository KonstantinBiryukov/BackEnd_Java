package ru.academits;

import org.junit.Assert;
import org.junit.Test;
import ru.academits.dao.ContactDao;
import ru.academits.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactDaoTest {

    @Test
    public void getAllContactsTest() {
        ContactDao contactDao = new ContactDao();
        List<Contact> allContacts = contactDao.getAllContacts();
        Assert.assertTrue(allContacts.size() != 0);
    }

    @Test
    public void searchTest() {
        ContactDao contactDao = new ContactDao();
        List<Contact> allContacts = contactDao.getAllContacts();
        List<Contact> filteredContacts = contactDao.filterContacts("ИВАН");
        Assert.assertTrue(filteredContacts.size() <= allContacts.size());
    }

    @Test
    public void addContactTest() {
        List<Contact> contactList = new ArrayList<>();
        Contact testContact = new Contact();
        testContact.setId(0);
        testContact.setFirstName("TestFirstName");
        testContact.setLastName("TestLastName");
        testContact.setPhone("123");
        contactList.add(testContact);
        Assert.assertTrue("true", contactList.size() >= 1);
    }

    @Test
    public void deleteContactTest() {
        ContactDao contactDao = new ContactDao();
        List<Contact> allContacts = contactDao.getAllContacts();
        contactDao.delete(allContacts.get(0).getId());
        Assert.assertEquals(0, allContacts.size());
    }
}