package com.tutorial.datalayer.infrastructure.repositories;

import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Closeable;

/**
 * Created by estoyanov on 3/19/15.
 */
public interface IContext extends Closeable {
    public EntityTransaction getTransaction();
    public CriteriaBuilder getCriteriaBuilder();
    public <T> IRepository<T> getRepository(Class<T> typeClass);
    public void save();
    public void rejectChanges();
}
