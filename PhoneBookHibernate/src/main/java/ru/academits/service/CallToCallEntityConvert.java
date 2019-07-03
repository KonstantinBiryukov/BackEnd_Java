package ru.academits.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.academits.dao.ContactDao;
import ru.academits.dto.CallDto;
import ru.academits.model.Call;
import ru.academits.model.Contact;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CallToCallEntityConvert {

    @Autowired
    ContactDao contactDao;

    public Call toEntity(CallDto callDto) {

        Call call = new Call();
        call.setId(callDto.getId());
        call.setCallDate(callDto.getCallDate());
        call.setCallNumber(callDto.getCallNumber());

        Contact contact = contactDao.findByPhone(callDto.getCallNumber()).get(0);
        call.setContact(contact);
        return call;
    }

    public List<Call> toEntityList(List<CallDto> callDtos) {
        List<Call> calls = new ArrayList<>();
        for (CallDto callDto : callDtos) {
            for (Call call : calls) {
                call.setId(callDto.getId());
                call.setCallDate(callDto.getCallDate());
                call.setCallNumber(callDto.getCallNumber());
            }
        }
        return calls;
    }
}