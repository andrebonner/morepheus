package com.training.morepheus.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T, E> {

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
    public Optional<T> getById(E id);

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
