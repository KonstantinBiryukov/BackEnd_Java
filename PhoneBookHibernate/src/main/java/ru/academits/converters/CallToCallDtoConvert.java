package ru.academits.converters;

import org.springframework.stereotype.Service;
import ru.academits.dto.CallDto;
import ru.academits.model.Call;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CallToCallDtoConvert {
    public CallDto toDto(Call call) {
        CallDto callDto = new CallDto();
        callDto.setId(call.getId());
        callDto.setCallDate(call.getCallDate());
        callDto.setCallNumber(call.getCallNumber());
        return callDto;
    }

    public List<CallDto> toDtoList(List<Call> calls) {
        List<CallDto> callDtos = new ArrayList<>();
        for (CallDto callDto : callDtos) {
            for (Call call : calls) {
                callDto.setId(call.getId());
                callDto.setCallDate(call.getCallDate());
                callDto.setCallNumber(call.getCallNumber());
            }
        }
        return callDtos;
    }
}