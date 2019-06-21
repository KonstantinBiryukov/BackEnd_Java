package ru.academits.controller;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.academits.dto.CallDto;
import ru.academits.dto.ContactDto;
import ru.academits.model.Call;
import ru.academits.model.Contact;
import ru.academits.model.ContactValidation;
import ru.academits.service.CallService;
import ru.academits.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.academits.service.ConvertService;

import java.util.List;

@Controller
@RequestMapping("/phoneBook/rpc/api/v1")
public class PhoneBookController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    private final ContactService contactService;
    private final CallService callService;
    private final ConvertService convertService;
//    @Autowired
//    private ConversionService convertService;

    @Autowired
    public PhoneBookController(ContactService contactService, CallService callService, ConvertService conversionService) {
        this.contactService = contactService;
        this.callService = callService;
        this.convertService = conversionService;
    }

    @RequestMapping(value = "getAllContacts", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> getAllContacts() {
        logger.info("called method getAllContacts");
        return contactService.getAllContacts();
    }

    @Transactional
    @RequestMapping(value = "addContact", method = RequestMethod.POST)
    @ResponseBody
    public ContactDto addContact(@RequestBody ContactDto contactDto) {
        Contact contact = convertService.convertContact(contactDto);

        contactService.addContact(contact);
        logger.info("Contact (Entity): " + toString(contact) + " is added");

        return convertService.convertContactDto(contact);
    }

    @Transactional
    @RequestMapping(value = "deleteContact", method = RequestMethod.POST)
    @ResponseBody
    public List<Contact> deleteContact(@RequestBody ContactDto contactDto) {
        Contact contact = convertService.convertContact(contactDto);
        contactService.deleteContact(contact.getId());

        logger.info("Contact (Entity): " + toString(contact) + " is removed");

        convertService.convertContactDto(contact);
        return contactService.getAllContacts();
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> searchContacts(@RequestParam(required = false, name = "query") String query) {
        logger.info("search is initialized, query string is \"" + query + "\"");
        return contactService.filterContacts(query);
    }

    @Transactional
    @RequestMapping(value = "addCall", method = RequestMethod.POST)
    @ResponseBody
    public CallDto addCall(@RequestBody CallDto callDto) {
        Call call = convertService.convertCall(callDto);
        callService.addCall(call);
        logger.info("Call (Entity): " + toString(call) + " is added");

        return convertService.convertCallDto(call);
    }

    @RequestMapping(value = "getAllCalls", method = RequestMethod.GET)
    @ResponseBody
    public List<Call> getAllCalls() {
        logger.info("called method getAllCalls");
        return callService.getAllCalls();
    }

    public String toString(Object object) {
        return ToStringBuilder.reflectionToString(object);
    }
}