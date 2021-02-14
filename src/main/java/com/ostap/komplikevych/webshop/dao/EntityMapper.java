package com.ostap.komplikevych.webshop.dao;

import java.sql.ResultSet;

/**
 * The interface Entity mapper creates an abstraction for mapping table rows.
 *
 * @param <T> the type parameter
 */
public interface EntityMapper<T> {
    /**
     * Map row.
     *
     * @param rs the resultSet
     * @return the Type
     */
    T mapRow(ResultSet rs);
}
