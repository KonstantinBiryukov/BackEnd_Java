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
import ru.academits.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/phoneBook/rpc/api/v1")
public class PhoneBookController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    private final ContactService contactService;
    private final CallService callService;
    private final ContactToContactDtoConvert contactToContactDtoConvert;
    private final CallToCallDtoConvert callToCallDtoConvert;
    private final ContactToContactEntityConvert contactToContactEntityConvert;
    private final CallToCallEntityConvert callToCallEntityConvert;

    @Autowired
    public PhoneBookController(ContactService contactService, CallService callService,
                               ContactToContactDtoConvert contactToContactDtoConvert,
                               CallToCallDtoConvert callToCallDtoConvert,
                               ContactToContactEntityConvert contactToContactEntityConvert,
                               CallToCallEntityConvert callToCallEntityConvert) {
        this.contactService = contactService;
        this.callService = callService;
        this.contactToContactDtoConvert = contactToContactDtoConvert;
        this.callToCallDtoConvert = callToCallDtoConvert;
        this.contactToContactEntityConvert = contactToContactEntityConvert;
        this.callToCallEntityConvert = callToCallEntityConvert;
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
        Contact contact = contactToContactEntityConvert.toEntity(contactDto);
        logger.info("ContactDto: " + toString(contactDto) + " is converted to Contact Entity");

        contactService.addContact(contact);
        logger.info("Contact (Entity): " + toString(contact) + " is added");

        ContactDto contactDtoConverted = contactToContactDtoConvert.toDto(contact);
        logger.info("Contact: " + toString(contact) + " is converted to Dto");

        return contactDtoConverted;
    }

    @Transactional
    @RequestMapping(value = "deleteContact", method = RequestMethod.POST)
    @ResponseBody
    public List<Contact> deleteContact(@RequestBody ContactDto contactDto) {
        Contact contact = contactToContactEntityConvert.toEntity(contactDto);
        logger.info("ContactDto: " + toString(contactDto) + " is converted to Contact Entity");

        contactService.deleteContact(contact.getId());
        logger.info("Contact (Entity): " + toString(contact) + " is removed");

        contactToContactDtoConvert.toDto(contact);
        logger.info("Contact: " + toString(contactDto) + " is converted to Dto");

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
        Call call = callToCallEntityConvert.toEntity(callDto);
        logger.info("CallDto: " + toString(callDto) + " is converted to Call Entity");

        callService.addCall(call);
        logger.info("Call (Entity): " + toString(call) + " is added");

        CallDto callDtoConverted = callToCallDtoConvert.toDto(call);
        logger.info("Call: " + toString(callDto) + " is converted to Dto");

        return callDtoConverted;
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