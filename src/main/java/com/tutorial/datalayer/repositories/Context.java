package com.tutorial.datalayer.repositories;

import com.tutorial.datalayer.infrastructure.repositories.IContext;
import com.tutorial.datalayer.infrastructure.repositories.IRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;

/**
 * Created by estoyanov on 3/19/15.
 */
public class Context implements IContext{

    private final EntityManager entityManager;
    private final EntityTransaction transaction;

    public Context(String configurationName){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(configurationName);
        this.entityManager = factory.createEntityManager();
        this.transaction = entityManager.getTransaction();
        this.transaction.begin();
    }

    @Override
    public EntityTransaction getTransaction() {
        return transaction;
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    @Override
    public <T> IRepository<T> getRepository(Class<T> typeClass) {
        return new Repository<T>(typeClass,entityManager);
    }

    @Override
    public void save() {
        this.transaction.commit();
    }

    @Override
    public void rejectChanges() {
        this.transaction.rollback();
    }

    @Override
    public void close() throws IOException {
        this.entityManager.close();
    }
}
