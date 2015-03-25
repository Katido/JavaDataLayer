package com.tutorial.datalayer.infrastructure.exceptions;

import javax.transaction.NotSupportedException;
import java.util.Objects;

/**
 * Created by estoyanov on 3/20/15.
 */
public final class ExceptionHelper {
    private ExceptionHelper(){}


    public static void throwIfClassNotSupported(Class<?> originClass, Class<?> typeClass) throws NotSupportedException {
        if(originClass != typeClass){
            throw new NotSupportedException(originClass.toString());
        }
    }


    public static void throwExceptionIfNull(Object instance) throws NullPointerException {
        if(instance == null){
            throw new NullPointerException(instance.toString());
        }
    }
}
