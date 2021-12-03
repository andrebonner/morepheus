package com.training.morepheus.controllers;

import java.util.List;

/**
 * @param <T>
 * @param <E>
 */
public interface ApiController<T, E> {

    /**
     * @param page
     * @param sort
     * @param order
     * @return
     */
    public List<T> getAll(Long page, String sort, String order);

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
