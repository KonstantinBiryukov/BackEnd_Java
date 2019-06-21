package ru.academits.service;

import org.springframework.stereotype.Service;
import ru.academits.dto.CallDto;
import ru.academits.model.Call;

@Service
public class CallToCallDtoConvert {
    public Call toEntity(CallDto callDto) {
        Call call = new Call();
        call.setId(callDto.getId());
        call.setCallDate(callDto.getCallDate());
        call.setCallNumber(callDto.getCallNumber());
        return call;
    }

    public CallDto toDto(Call call) {
        CallDto callDto = new CallDto();
        callDto.setId(call.getId());
        callDto.setCallDate(call.getCallDate());
        callDto.setCallNumber(call.getCallNumber());
        return callDto;
    }
}