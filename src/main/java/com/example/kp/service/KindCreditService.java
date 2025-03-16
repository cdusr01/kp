package com.example.kp.service;


import com.example.kp.model.KindCredit;
import com.example.kp.repository.KindCreditDao;

import java.util.List;

public class KindCreditService {
    private KindCreditDao kindCreditDAO = new KindCreditDao();

    public KindCreditService() {
    }

    public List<KindCredit> findAll() {
        return kindCreditDAO.findAll();
    }

    public KindCredit findOne(final long id) {
        return kindCreditDAO.findOne(id);
    }

    public void save(final KindCredit entity)
    {
        if (entity == null)
            return;
        kindCreditDAO.save(entity);
    }

    public void update(final KindCredit entity)
    {
        if (entity == null)
            return;
        kindCreditDAO.update(entity);
    }

    public void delete(final KindCredit entity)
    {
        if (entity == null)
            return;
        kindCreditDAO.delete(entity);
    }

    public void deleteById(final Long id)
    {
        if (id == null)
            return;
        kindCreditDAO.deleteById(id);
    }
}
