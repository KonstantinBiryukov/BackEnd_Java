package ru.academits.dao;

import org.springframework.stereotype.Repository;
import ru.academits.model.Contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ContactDaoImpl extends GenericDaoImpl<Contact, Long> implements ContactDao {
    public ContactDaoImpl() {
        super(Contact.class);
    }

    @Override
    public List<Contact> getAllContacts() {
        return findAll();
    }

    @Override
    public void save(Contact contact) {
        saveOrUpdate(contact);
    }

    @Override
    public void deleteContact(int contactId) {
        deleteContactById(contactId);
    }

    @Override
    public List<Contact> findByPhone(String phone) {
        Map condition = new HashMap<String, Object>();
        condition.put("phone", phone);
        return findAllByMulti(condition);
    }

    @Override
    public List<Contact> findContacts(String query) {
        return filterContacts(query);
    }
}