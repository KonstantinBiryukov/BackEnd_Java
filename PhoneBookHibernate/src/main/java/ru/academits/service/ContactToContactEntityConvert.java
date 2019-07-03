package ru.academits.service;

import org.springframework.stereotype.Service;
import ru.academits.dto.ContactDto;
import ru.academits.model.Contact;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class ContactToContactEntityConvert {

    public Contact toEntity(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setId(contactDto.getId());
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setPhone(contactDto.getPhone());
        contact.setRemoved(contactDto.isRemoved());

        return contact;
    }

    public List<Contact> toEntityList(List<ContactDto> contactDtos) {
        List<Contact> contacts = new ArrayList<>();
        for (ContactDto contactDto : contactDtos) {
            for (Contact contact : contacts) {
                contact.setId(contactDto.getId());
                contact.setFirstName(contactDto.getFirstName());
                contact.setLastName(contactDto.getLastName());
                contact.setPhone(contactDto.getPhone());
                contact.setRemoved(contactDto.isRemoved());
            }
        }
        return contacts;
    }
}