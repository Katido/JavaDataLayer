package com.tutorial.datalayer.repositories;

import com.tutorial.datalayer.infrastructure.exceptions.ExceptionHelper;
import com.tutorial.datalayer.infrastructure.repositories.IRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;


/**
 * Created by estoyanov on 3/19/15.
 */
public class Repository<T> implements IRepository<T> {

    private final EntityManager entityManager;
    private final Class<T> typeClass;

    public Repository(Class<T> typeClass,  EntityManager entityManager){

        if(entityManager == null){
            throw new NullPointerException("entityManager");
        }

        if(typeClass == null){
            throw new NullPointerException("typeClass");
        }

        this.entityManager = entityManager;
        this.typeClass = typeClass;
    }

    @Override
    public void add(T instance) throws Exception {
        ExceptionHelper.throwExceptionIfNull(instance);
        ExceptionHelper.throwIfClassNotSupported(instance.getClass(), this.typeClass);
        entityManager.persist(instance);
    }


    @Override
    public T getById(long id) {
        return entityManager.find(this.typeClass,id);
    }


    @Override
    public void remove(T instance) throws Exception {
        ExceptionHelper.throwExceptionIfNull(instance);
        ExceptionHelper.throwIfClassNotSupported(instance.getClass(), this.typeClass);
        entityManager.remove(instance);
    }



    @Override
    public CriteriaQuery<T> createQuery() {
        return entityManager.getCriteriaBuilder().createQuery(this.typeClass);
    }

    @Override
    public Query executeQuery(CriteriaQuery<T> query) {
        return entityManager.createQuery(query);
    }
}
