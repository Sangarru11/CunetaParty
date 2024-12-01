package com.github.Sangarru11.CunetaParty.model.DAO;

import com.github.Sangarru11.CunetaParty.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.CunetaParty.model.entity.empleados;
import com.github.Sangarru11.CunetaParty.model.entity.vehiculo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class vehiculoDAO implements DAO<vehiculo, String> {
    private static final String FINDBYMATRICULA = "SELECT v.ID, v.ModeloVehiculo, v.MatriculaVehiculo FROM vehiculos AS v WHERE v.matricula=?";
    private static final String FINDBYID = "SELECT v.ID, v.ModeloVehiculo, v.MatriculaVehiculo FROM vehiculos AS v WHERE v.ID=?";
    private static final String INSERT = "INSERT INTO vehiculos (ModeloVehiculo, MatriculaVehiculo) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE vehiculos SET ModeloVehiculo=?, MatriculaVehiculo=? WHERE ID=?";
    private static final String DELETE = "DELETE FROM vehiculos WHERE ID=?";
    private Connection connection;

    public vehiculoDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    @Override
    public vehiculo save(vehiculo entity) {
        vehiculo result = entity;
        if (entity != null) {
            String matricula = entity.getMatriculaVehiculo();
            if (matricula != null) {
                vehiculo isInDataBase = findByDNI(matricula);
                if (isInDataBase == null) {
                    try (PreparedStatement pst = connection.prepareStatement(INSERT)) {
                        pst.setString(1, entity.getModeloVehiculo());
                        pst.setString(2, entity.getMatriculaVehiculo());
                        pst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement pst = connection.prepareStatement(UPDATE)) {
                        pst.setString(1, entity.getModeloVehiculo());
                        pst.setString(2, entity.getMatriculaVehiculo());
                        pst.setInt(3, entity.getId());
                        pst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public vehiculo delete(vehiculo entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = connection.prepareStatement(DELETE)) {
                pst.setInt(1, entity.getId());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    @Override
    public empleados adminManage(empleados entity) throws SQLException {
        return null;
    }

    @Override
    public vehiculo findById(String key) {
        vehiculo result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYID)) {
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    vehiculo v = new vehiculo();
                    v.setId(res.getInt("ID"));
                    v.setModeloVehiculo(res.getString("ModeloVehiculo"));
                    v.setMatriculaVehiculo(res.getString("MatriculaVehiculo"));
                    result = v;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<vehiculo> findbyAll() {
        List<vehiculo> result = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM vehiculos")) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    vehiculo v = new vehiculo();
                    v.setId(res.getInt("ID"));
                    v.setModeloVehiculo(res.getString("ModeloVehiculo"));
                    v.setMatriculaVehiculo(res.getString("MatriculaVehiculo"));
                    result.add(v);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public vehiculo findByDate(String key) {
        return null;
    }

    @Override
    public vehiculo findByDNI(String key) {
        vehiculo result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYMATRICULA)) {
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    vehiculo v = new vehiculo();
                    v.setId(res.getInt("ID"));
                    v.setModeloVehiculo(res.getString("ModeloVehiculo"));
                    v.setMatriculaVehiculo(res.getString("MatriculaVehiculo"));
                    result = v;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public vehiculo findByName(String key) {
        return null;
    }

    @Override
    public void close() throws IOException {

    }

    public static vehiculoDAO build() {
        return new vehiculoDAO();
    }
}