package org.javageek.katalogo.dao;

public interface BaseDao<T> {
    void save(T t);
}
