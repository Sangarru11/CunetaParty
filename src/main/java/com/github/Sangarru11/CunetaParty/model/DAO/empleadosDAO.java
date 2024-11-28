package com.github.Sangarru11.CunetaParty.model.DAO;

import com.github.Sangarru11.CunetaParty.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.CunetaParty.model.entity.empleados;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class empleadosDAO implements DAO<empleados,String>{
    private static final String FINDBYID = "SELECT e.ID, e.Nombre, e.DNI, e.Especialidad, e.password, e.Admin FROM empleados AS e WHERE e.ID = ?";
    private static final String FINDBYNAME = "SELECT e.ID, e.Nombre, e.DNI, e.Especialidad, e.password, e.Admin FROM empleados AS e WHERE e.Nombre=?";
    private static final String INSERT = "INSERT INTO empleados (Nombre, DNI, Especialidad, password, Admin) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE empleados SET name=? WHERE ID=?";
    private static final String UPDATE_ADMIN_STATUS = "UPDATE empleados SET Admin=? WHERE ID=?";
    private static final String ADMIN = "SELECT e.ID, e.dni, e.name, e.password, e.Admin FROM empleados AS e WHERE e.Admin=?";
    private static final String DELETE = "DELETE FROM empleados WHERE ID=?";
    private Connection connection;

    public empleadosDAO() {
        connection = ConnectionMariaDB.getConnection();
    }
    @Override
    public empleados save(empleados entity) {
        empleados result = entity;
        if (entity != null) {
            String dni = entity.getDNI();
            if (dni != null) {
                empleados isInDataBase = findByDNI(dni);
                if (isInDataBase == null) {
                    try (PreparedStatement pst = connection.prepareStatement(INSERT)) {
                        pst.setString(2, dni);
                        pst.setString(1, entity.getNombre());
                        pst.setString(3, entity.getEspecialidad());
                        pst.setString(4, entity.getPassword());
                        pst.setBoolean(5, entity.isAdmin());

                        pst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement pst = connection.prepareStatement(UPDATE)) {
                        pst.setString(1, entity.getNombre());
                        pst.setString(2, entity.getPassword());
                        pst.setInt(4, entity.getId_empleado());
                        pst.setBoolean(3, entity.isAdmin());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public empleados delete(empleados entity) throws SQLException {
        if (entity != null){
            try (PreparedStatement pst = connection.prepareStatement(DELETE)){
                pst.setInt(1, entity.getId_empleado());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    @Override
    public empleados adminManage(empleados entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = connection.prepareStatement(ADMIN)) {
                pst.setBoolean(1, entity.isAdmin());
                pst.executeUpdate();
                entity.setAdmin(!entity.isAdmin());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    @Override
    public empleados findById(String key) {
        empleados result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYID)){
            pst.setString(1,key);
            try(ResultSet res = pst.executeQuery()){
                if (res.next()){
                    empleados e = new empleados();
                    e.setId_empleado(res.getInt("ID"));
                    e.setDNI(res.getString("DNI"));
                    e.setNombre(res.getString("Nombre"));
                    e.setEspecialidad(res.getString("Especialidad"));
                    e.setPassword(res.getString("password"));
                    e.setAdmin(res.getBoolean("Admin"));
                    result = e;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<empleados> findbyAll() {
        List<empleados> result = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM empleados")){
            try(ResultSet res = pst.executeQuery()){
                while (res.next()){
                    empleados e = new empleados();
                    e.setId_empleado(res.getInt("ID"));
                    e.setDNI(res.getString("DNI"));
                    e.setNombre(res.getString("Nombre"));
                    e.setEspecialidad(res.getString("Especialidad"));
                    e.setPassword(res.getString("password"));
                    e.setAdmin(res.getBoolean("Admin"));
                    result.add(e);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public empleados findByDate(String key) {
        return null;
    }

    @Override
    public empleados findByDNI(String key) {
        return null;
    }

    @Override
    public empleados findByName(String key) {
        empleados result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYNAME)){
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()){
                if (res.next()){
                    empleados e = new empleados();
                    e.setId_empleado(res.getInt("ID"));
                    e.setNombre(res.getString("Nombre"));
                    e.setDNI(res.getString("DNI"));
                    e.setEspecialidad(res.getString("Especialidad"));
                    e.setPassword(res.getString("password"));
                    e.setAdmin(res.getBoolean("Admin"));
                result = e;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {

    }
    public void updateAdminStatus(int employeeId, boolean isAdmin) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(UPDATE_ADMIN_STATUS)) {
            pst.setBoolean(1, isAdmin);
            pst.setInt(2, employeeId);
            pst.executeUpdate();
        }
    }
    public static empleadosDAO build(){
        return new empleadosDAO();
    }
}
