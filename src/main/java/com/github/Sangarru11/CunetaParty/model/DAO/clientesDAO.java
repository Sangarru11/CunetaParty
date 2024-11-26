package com.github.Sangarru11.CunetaParty.model.DAO;

import com.github.Sangarru11.CunetaParty.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.CunetaParty.model.entity.clientes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class clientesDAO implements DAO <clientes,String>{
    private static final String FINDBYDNI = "SELECT c.ID, c.Nombre, c.Telefono, c.Modelo_Vehiculo, c.Matricula FROM clientes AS c WHERE c.Nombre=?";
    private static final String FINDBYID = "SELECT c.id_cliente, c.dni, c.Nombre, c.Telefono, c.Modelo_Vehiculo, c.Matricula FROM clientes AS c WHERE c.id_cliente = ?";
    private static final String FINDBYNAME = "SELECT c.ID, c.Nombre, c.Telefono, c.Modelo_Vehiculo, c.Matricula FROM clientes AS c WHERE c.Nombre=?";
    private static final String INSERT = "INSERT INTO clientes (dni, Nombre, Telefono, Modelo_Vehiculo, Matricula) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE clientes SET name=? WHERE id_cliente=?";
    private static final String DELETE = "DELETE FROM clientes WHERE id_cliente=?";
    private Connection connection;

    public clientesDAO() {
        connection = ConnectionMariaDB.getConnection();
    }
    @Override
    public clientes save(clientes entity) {
        clientes result = entity;
        if (entity != null) {
            String dni = entity.getDni();
            if (dni != null) {
                clientes isInDataBase = findByDNI(dni);
                if (isInDataBase == null) {
                    try (PreparedStatement pst = connection.prepareStatement(INSERT)) {
                        pst.setString(1, dni);
                        pst.setString(2, entity.getNombre());
                        pst.setString(3, entity.getTelefono());
                        pst.setString(4, entity.getModelo_vehiculo());
                        pst.setString(5, entity.getMatricula());
                        pst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement pst = connection.prepareStatement(UPDATE)) {
                        pst.setString(1, entity.getNombre());
                        pst.setInt(2, entity.getId_cliente());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public clientes delete(clientes entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = connection.prepareStatement(DELETE)) {
                pst.setInt(1, entity.getId_cliente());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    @Override
    public clientes findById(String key) {
        clientes result = null;
        try(PreparedStatement pst = connection.prepareStatement(FINDBYID)){
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()){
                if (res.next()){
                    clientes c = new clientes();
                    c.setId_cliente(res.getInt("id_cliente"));
                    c.setDni(res.getString("dni"));
                    c.setNombre(res.getString("name"));
                    c.setTelefono(res.getString("telefono"));
                    c.setModelo_vehiculo(res.getString("modelo_vehiculo"));
                    c.setMatricula(res.getString("matricula"));
                    result = c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public List<clientes> findbyAll() {
        List<clientes> result = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM clientes")){
            try (ResultSet res = pst.executeQuery()){
                while (res.next()){
                    clientes c = new clientes();
                    c.setId_cliente(res.getInt("ID"));
                    c.setDni(res.getString("dni"));
                    c.setNombre(res.getString("Nombre"));
                    c.setTelefono(res.getString("Telefono"));
                    c.setMatricula(res.getString("Matricula"));
                    c.setModelo_vehiculo(res.getString("Modelo_Vehiculo"));
                    result.add(c);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public clientes findByName(String key) {
        clientes result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYNAME)) {
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    clientes c = new clientes();
                    c.setId_cliente(res.getInt("ID"));
                    c.setDni(res.getString("dni"));
                    c.setNombre(res.getString("Nombre"));
                    c.setTelefono(res.getString("Telefono"));
                    c.setMatricula(res.getString("Matricula"));
                    c.setModelo_vehiculo(res.getString("Modelo_Vehiculo"));
                    result = c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public clientes findByDate(String key) {
        return null;
    }
    @Override
    public clientes findByDNI(String key) {
        clientes result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYDNI)){
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()){
                if (res.next()){
                    clientes c = new clientes();
                    c.setId_cliente(res.getInt("ID"));
                    c.setDni(res.getString("dni"));
                    c.setNombre(res.getString("Nombre"));
                    c.setTelefono(res.getString("Telefono"));
                    c.setMatricula(res.getString("Matricula"));
                    c.setModelo_vehiculo(res.getString("Modelo_Vehiculo"));
                    result = c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {
    }
    public static clientesDAO build(){
        return new clientesDAO();
    }
}