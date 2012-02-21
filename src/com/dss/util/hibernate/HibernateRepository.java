package com.dss.util.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.dss.core.IRepository;
import com.dss.storage.model.DocumentDirectory;
import com.dss.util.log.LogUtil;

public class HibernateRepository<T extends HibernateEntity>
        extends HibernateDaoSupport
        implements IRepository<T>
{

    public HibernateRepository(SessionFactory factory) {
        setSessionFactory(factory);
    }

    @Transactional
    public void delete(T entity)
    {
        LogUtil.debug(getClass(), "delete: " + entity);
        getHibernateTemplate().delete(entity);
    }

    @Transactional
    public void saveOrUpdate(T entity)
    {
        LogUtil.debug(getClass(), "save: " + entity);
        getHibernateTemplate().saveOrUpdate(entity);
    }

}
