package com.github.Sangarru11.CunetaParty.model.DAO;

import com.github.Sangarru11.CunetaParty.model.entity.Employee;
import com.github.Sangarru11.CunetaParty.model.entity.Repairs;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T,K> extends Closeable {
    T save(T entity);
    T delete(T entity) throws SQLException;

    Employee adminManage(Employee entity) throws SQLException;

    Repairs findByPlateNumber(String key);

    T findById(String key);

    List<T> findbyAll();

    T findByDate(String key);


    T findByDNI(String key);

    T findByName(String key);
}

