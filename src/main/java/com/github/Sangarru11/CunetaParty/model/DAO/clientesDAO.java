package com.github.Sangarru11.CunetaParty.model.DAO;

import com.github.Sangarru11.CunetaParty.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.CunetaParty.model.entity.clientes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class clientesDAO implements DAO <clientes,String>{
    private static final String FINDBYDNI = "SELECT c.ID, c.Nombre, c.Telefono, c.Modelo_Vehiculo, c.Matricula FROM clientes AS c WHERE c.Nombre=?";

    private Connection connection;

    public clientesDAO() {
        connection = ConnectionMariaDB.getConnection();
    }
    @Override
    public clientes save(clientes entity) {
        return null;
    }

    @Override
    public clientes delete(clientes entity) throws SQLException {
        return null;
    }

    @Override
    public clientes findById(String key) {
        return null;
    }

    @Override
    public List<clientes> findbyAll() {
        return null;
    }

    @Override
    public clientes findByDate(String key) {
        return null;
    }

    @Override
    public clientes findByDNI(String key) {
        return null;
    }

    @Override
    public clientes findByName(String key) {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
