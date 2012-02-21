package com.dss.core;

public interface IRepository<T>
{
    void delete(T entity);
    void saveOrUpdate(T entity);

}