package ru.academits.controller;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.academits.model.Call;
import ru.academits.model.Contact;
import ru.academits.model.ContactValidation;
import ru.academits.service.CallService;
import ru.academits.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/phoneBook/rpc/api/v1")
public class PhoneBookController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    private final ContactService contactService;
    private final CallService callService;

    @Autowired
    public PhoneBookController(ContactService contactService, CallService callService) {
        this.contactService = contactService;
        this.callService = callService;
    }

    @RequestMapping(value = "getAllContacts", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> getAllContacts() {
        logger.info("called method getAllContacts");
        return contactService.getAllContacts();
    }

    @RequestMapping(value = "addContact", method = RequestMethod.POST)
    @ResponseBody
    public ContactValidation addContact(@RequestBody Contact contact) {
        return contactService.addContact(contact);
    }

    @RequestMapping(value = "deleteContact", method = RequestMethod.POST)
    @ResponseBody
    public List<Contact> deleteContact(@RequestBody Contact contact) {
//        logger.info(toString(contact) + " is removed");
        contactService.deleteContact(contact.getId());
        return contactService.getAllContacts();
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> searchContacts(@RequestParam(required = false, name = "query") String query) {
//        logger.info("search is initialized, query string is \"" + query + "\"");
        return contactService.filterContacts(query);
    }

    @RequestMapping(value = "addCall", method = RequestMethod.POST)
    @ResponseBody
    public void addContact(@RequestBody Call call) {
        callService.addCall(call);
    }

    @RequestMapping(value = "getAllCalls", method = RequestMethod.GET)
    @ResponseBody
    public List<Call> getAllCalls() {
        logger.info("called method getAllCalls");
        return callService.getAllCalls();
    }

    public String toString(Contact contact) {
        return ToStringBuilder.reflectionToString(contact);
    }
}