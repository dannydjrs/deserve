package com.dannydjrs.deserve.objectmanager;

/**
 * @author dannydjrs
 */
public interface ObjectFactory<T> {
    /**
     * 
     * @return
     */
    public T create();
}
