package ru.academits.dao;

import ru.academits.model.Contact;

import java.util.List;

public interface ContactDao {
    List<Contact> getAllContacts();

    void save(Contact contact);

    void deleteContact(int contactId);

    List<Contact> findByPhone(String phone);

    List<Contact> findContacts(String query);
}