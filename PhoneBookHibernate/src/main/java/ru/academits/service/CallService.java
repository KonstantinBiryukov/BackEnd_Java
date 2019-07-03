package ru.academits.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.academits.dao.CallDao;
import ru.academits.dao.ContactDao;
import ru.academits.model.Call;
import ru.academits.model.Contact;

import java.util.List;

@Service
public class CallService {
    private final CallDao callDao;
    private final ContactDao contactDao;

    @Autowired
    public CallService(CallDao callDao, ContactDao contactDao) {
        this.callDao = callDao;
        this.contactDao = contactDao;
    }

    public void addCall(Call call) {
        // setContact to assign foreign key in Call entity
        Contact contact = contactDao.findByPhone(call.getCallNumber()).get(0);
        call.setContact(contact);

        callDao.save(call);
    }

    public List<Call> getAllCalls() {
        return callDao.getAllCalls();
    }
}