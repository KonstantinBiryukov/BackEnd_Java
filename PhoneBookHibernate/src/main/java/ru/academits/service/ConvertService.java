package ru.academits.service;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.academits.controller.PhoneBookController;
import ru.academits.dto.CallDto;
import ru.academits.dto.ContactDto;
import ru.academits.model.Call;
import ru.academits.model.Contact;

@Service
public class ConvertService {
    private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    ContactToContactDtoConvert contactToContactDtoConvert;

    @Autowired
    CallToCallDtoConvert callToCallDtoConvert;

    public Contact convertContact(ContactDto contactDto) {
        Session session = sessionFactory.getCurrentSession();
        Contact contact = contactToContactDtoConvert.toEntity(contactDto);
        session.saveOrUpdate(contact);

        logger.info("ContactDto: " + toString(contactDto) + " is converted");
        return contact;
    }

    public Call convertCall(CallDto callDto) {
        Session session = sessionFactory.getCurrentSession();
        Call call = callToCallDtoConvert.toEntity(callDto);
        session.saveOrUpdate(call);

        logger.info("CallDto: " + toString(callDto) + " is converted");
        return call;
    }


    public String toString(Object object) {
        return ToStringBuilder.reflectionToString(object);
    }
}