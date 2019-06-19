package ru.academits.dao;

import ru.academits.model.Call;

import java.util.List;

public interface CallDao {
    List<Call> getAllCalls();

    void save(Call call);
}