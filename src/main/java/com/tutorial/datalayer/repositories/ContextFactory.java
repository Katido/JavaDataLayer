package com.tutorial.datalayer.repositories;

import com.tutorial.datalayer.infrastructure.repositories.IContext;
import com.tutorial.datalayer.infrastructure.repositories.IContextFactory;

/**
 * Created by estoyanov on 3/20/15.
 */
public class ContextFactory implements IContextFactory {
    @Override
    public IContext create() {
        return new Context("persistenceUnit");
    }
}
