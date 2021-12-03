package com.training.morepheus.services;

import java.util.List;

public interface ApiService<T, E> {

    /**
     * @param page
     * @param sort
     * @param order
     * @return
     */
    public List<T> getAll(E page, String sort, String order);

    /**
     * @param id
     * @return
     */
    public T getById(E id);

    /**
     * @param t
     * @return
     */
    public T create(T t);

    /**
     * @param id
     * @param t
     * @return
     */
    public T update(E id, T t);

    /**
     * @param id
     */
    public void delete(E id);
}
