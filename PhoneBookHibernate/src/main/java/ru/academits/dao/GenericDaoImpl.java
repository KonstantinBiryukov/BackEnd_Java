package ru.academits.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.academits.controller.PhoneBookController;
import ru.academits.model.Contact;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Transactional
public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
    private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    @Autowired
    private SessionFactory sessionFactory;

    protected Class<T> clazz;

    public GenericDaoImpl(Class<T> type) {
        this.clazz = type;
    }

    @Transactional
    @Override
    public void saveOrUpdate(T obj) {
        sessionFactory.getCurrentSession().saveOrUpdate(obj);
    }

    @Override
    public T getById(PK id) {
        return (T) sessionFactory.getCurrentSession().get(clazz, id);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public void deleteContactById(int contactId) {
        Session session = sessionFactory.getCurrentSession();
        Contact contact = session.get(Contact.class, contactId);
        contact.setRemoved(true);
        // to delete from Database use: (Not recommended!)
        // session.delete(myObject);
        session.flush();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllByMulti(Map<String, Object> condition) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
        condition.forEach((k, v) -> {
            if (k != null) {
                criteria.add(Restrictions.eq(k, v));
            }
        });
        return (List<T>) criteria.list();
    }

    @Transactional
    @Override
    public List<T> findAll() {
        return findAll(null);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllCalls() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
        return (List<T>) criteria.list();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(Order order) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
        if (order != null) {
            criteria.addOrder(order);
        }
        criteria.add(Restrictions.eq("isRemoved", false));
        return (List<T>) criteria.list();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<T> filterContacts(String query) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
        criteria
                .add(Restrictions.eq("isRemoved", false))
                .add(Restrictions.or(
                        Restrictions.like("firstName", "%" + query + "%"),
                        Restrictions.like("lastName", "%" + query + "%"),
                        Restrictions.like("phone", "%" + query + "%")
                        )
                );
        return criteria.list();
    }
}