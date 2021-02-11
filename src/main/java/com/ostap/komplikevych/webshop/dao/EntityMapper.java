package com.ostap.komplikevych.webshop.dao;

import java.sql.ResultSet;

public interface EntityMapper<T> {
    T mapRow(ResultSet rs);
}
