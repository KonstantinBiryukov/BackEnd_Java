package ru.academits.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.academits.dao.CallDao;
import ru.academits.model.Call;

import java.util.List;

@Service
public class CallService {
    private final CallDao callDao;

    @Autowired
    public CallService(CallDao callDao) {
        this.callDao = callDao;
    }

    public void addCall(Call call) {
        callDao.save(call);
    }

    public List<Call> getAllCalls() {
        return callDao.getAllCalls();
    }
}