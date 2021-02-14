package com.ostap.komplikevych.webshop.dao;

/**
 * This interface contains main CRUD operations.
 *
 * @param <T>  Entity type
 * @param <PK> Primary Key
 * @author Ostap Komplikevych
 */
public interface Crud<T, PK> {
    /**
     * This methode creates an entity and return it`s primary key index.
     *
     * @param entity entity
     * @return PK
     */
    PK create(T entity);

    /**
     * This methode reads an entity by it`s PK.
     *
     * @param id Primary Key index
     * @return T
     */
    T read(PK id);

    /**
     * This methode updates an entity.
     *
     * @param entity entity
     */
    void update(T entity);

    /**
     * This methode reads an entity by it`s PK.
     *
     * @param entity entity
     */
    void delete(T entity);
}
