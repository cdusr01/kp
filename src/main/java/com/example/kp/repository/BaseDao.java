package com.example.kp.repository;


import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.kp.util.HibernateUtil;

import java.util.List;

public abstract class BaseDao<T>  {
    private Class<T> clazz;

    public BaseDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected Session getCurrentSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }


    public void save(final T entity) {
        Session session = getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.save(entity);
        tx1.commit();
        session.close();
    }

    public void update(final T entity) {
        Session session = getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entity);
        tx1.commit();
        session.close();
    }

    public void delete(final T entity) {
        Session session = getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(entity);
        tx1.commit();
        session.close();
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }

    public T findOne(final long id) {
        return getCurrentSession().get(clazz, id);
    }


    public List<T> findAll() {
        List<T> items = (List<T>) HibernateUtil.getSessionFactory().openSession().createQuery("from " + clazz.getName()).list();
        return items;
    }
}