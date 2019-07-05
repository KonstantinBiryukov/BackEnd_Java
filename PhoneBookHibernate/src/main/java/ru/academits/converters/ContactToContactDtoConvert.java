package ru.academits.converters;

import org.springframework.stereotype.Service;
import ru.academits.dto.ContactDto;
import ru.academits.model.Contact;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class ContactToContactDtoConvert {
    public ContactDto toDto(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setFirstName(contact.getFirstName());
        contactDto.setLastName(contact.getLastName());
        contactDto.setPhone(contact.getPhone());
        contactDto.setRemoved(contact.isRemoved());

        return contactDto;
    }

    public List<ContactDto> toDtoList(List<Contact> contacts) {
        List<ContactDto> contactDtos = new ArrayList<>();
        for (ContactDto contactDto : contactDtos) {
            for (Contact contact : contacts) {
                contactDto.setId(contact.getId());
                contactDto.setFirstName(contact.getFirstName());
                contactDto.setLastName(contact.getLastName());
                contactDto.setPhone(contact.getPhone());
                contactDto.setRemoved(contact.isRemoved());
            }
        }
        return contactDtos;
    }
}