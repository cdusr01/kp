package com.example.kp.service;


import com.example.kp.model.Credit;
import com.example.kp.repository.CreditDao;

import java.util.List;

public class CreditService {
    private CreditDao creditDao = new CreditDao();

    public CreditService() {
    }

    public List<Credit> findAll() {
        return creditDao.findAll();
    }

    public Credit findOne(final long id) {
        return creditDao.findOne(id);
    }

    public void save(final Credit entity)
    {
        if (entity == null)
            return;
        creditDao.save(entity);
    }

    public void update(final Credit entity)
    {
        if (entity == null)
            return;
        creditDao.update(entity);
    }

    public void delete(final Credit entity)
    {
        if (entity == null)
            return;
        creditDao.delete(entity);
    }

    public void deleteById(final Long id)
    {
        if (id == null)
            return;
        creditDao.deleteById(id);
    }
}
