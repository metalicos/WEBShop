package com.ostap.komplikevych.webshop.dao;

import java.io.Serializable;

public interface Crud<T, PK> {
        PK create(T entity);
        T read(PK id);
        void update(T entity);
        void delete(T entity);
}
