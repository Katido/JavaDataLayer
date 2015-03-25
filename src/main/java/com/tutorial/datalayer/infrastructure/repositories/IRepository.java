package com.tutorial.datalayer.infrastructure.repositories;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.NotSupportedException;

/**
 * Created by estoyanov on 3/19/15.
 */
public interface IRepository<T> {
    public void add(T instance) throws Exception;
    public T getById(long id);
    public void remove(T instance) throws Exception;
    public CriteriaQuery<T> createQuery();
    public Query executeQuery(CriteriaQuery<T> query);
}
