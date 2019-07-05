package ru.academits.converters;

import org.springframework.stereotype.Service;
import ru.academits.dto.CallDto;
import ru.academits.model.Call;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CallToCallEntityConvert {
    public Call toEntity(CallDto callDto) {
        Call call = new Call();
        call.setId(callDto.getId());
        call.setCallDate(callDto.getCallDate());
        call.setCallNumber(callDto.getCallNumber());
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