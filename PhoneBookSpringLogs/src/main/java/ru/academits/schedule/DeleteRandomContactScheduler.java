package ru.academits.schedule;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.academits.model.Contact;
import ru.academits.phonebook.PhoneBookController;
import ru.academits.service.ContactService;

import java.util.List;

@Component
@EnableScheduling
@EnableAutoConfiguration
public class DeleteRandomContactScheduler {
    private final ContactService contactService;
    private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    @Autowired
    public DeleteRandomContactScheduler(ContactService contactService) {
        this.contactService = contactService;
    }

    @Scheduled(initialDelay = 2000, fixedRate = 8000)
    public void deleteRandomContactScheduler() {
        System.out.println("Scheduler is working...");

        List<Contact> contactList = contactService.getAllContacts();
        int contactsListSize = contactList.size();
        int randomId = (int) (Math.random() * contactsListSize - 1);
        if (contactsListSize < 1) {
            logger.info("Trying to delete a random contact... Contacts list is empty");
        } else {
            logger.info("contact#" + randomId + " is removed");
            contactService.deleteContact(contactList.get(randomId).getId());
        }
    }

    public String toString(Contact contact) {
        return ToStringBuilder.reflectionToString(contact);
    }
}