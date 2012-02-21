package com.dss.util.hibernate;

import com.dss.core.IEntity;
import com.dss.core.IRepository;

public abstract class HibernateEntity
        implements IEntity
{
    private long id;
    private IRepository<? super HibernateEntity> repository;

    protected long getId()
    {
        return this.id;
    }   

    protected void setId(long id)
    {
        this.id = id;
    }

    protected void setRepository(IRepository<? super HibernateEntity> repository)
    {
        this.repository = repository;
    }

    protected IRepository<? super HibernateEntity> getRepository()
    {
        if (repository == null)
            throw new IllegalArgumentException("must provide a repository using SetRepository");
        return this.repository;
    }

    @Override
    public void remove()
    {
        getRepository().delete(this);
    }

    @Override
    public void save()
    {
        getRepository().saveOrUpdate(this);
    }

}
