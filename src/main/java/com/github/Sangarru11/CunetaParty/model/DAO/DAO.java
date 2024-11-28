package com.github.Sangarru11.CunetaParty.model.DAO;

import com.github.Sangarru11.CunetaParty.model.entity.empleados;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T,K> extends Closeable {
    T save(T entity);
    T delete(T entity) throws SQLException;
    empleados adminManage(empleados entity) throws SQLException;
    T findById(String key);
    List<T> findbyAll();
    T findByDate(String key);
    T findByDNI(String key);
    T findByName(String key);

}
