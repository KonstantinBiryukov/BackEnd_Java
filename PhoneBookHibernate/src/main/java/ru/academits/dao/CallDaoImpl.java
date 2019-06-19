package ru.academits.dao;


import org.springframework.stereotype.Repository;
import ru.academits.model.Call;

import java.util.List;

@Repository
public class CallDaoImpl extends GenericDaoImpl<Call, Long> implements CallDao {
    public CallDaoImpl() {
        super(Call.class);
    }

    @Override
    public List<Call> getAllCalls() {
        return findAllCalls();
    }

    @Override
    public void save(Call call) {
        saveOrUpdate(call);
    }
}