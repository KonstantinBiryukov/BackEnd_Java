package ru.academits.dao;

import org.hibernate.criterion.Order;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<T, PK extends Serializable> {
    @Transactional
    void saveOrUpdate(T obj);

    T getById(PK id);

    @Transactional
    @SuppressWarnings("unchecked")
    void deleteContactById(int contactId);

    List<T> findAllByMulti(Map<String, Object> condition);

    @Transactional
    List<T> findAll();

    @Transactional
    List<T> findAllCalls();

    @Transactional
    @SuppressWarnings("unchecked")
    List<T> findAll(Order order);

    List<T> filterContacts(String query);
}
