package com.github.Sangarru11.CunetaParty.model.DAO;

import com.github.Sangarru11.CunetaParty.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.CunetaParty.model.entity.empleados;
import com.github.Sangarru11.CunetaParty.model.entity.reservas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class reservasDAO implements DAO<reservas,String>{
    private static final String FINDBYDATE = "SELECT r.Fecha, r.Estado FROM reservas AS r WHERE r.Estado = ?";
    private static final String INSERT = "INSERT INTO reservas (Fecha, Estado) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE reservas SET Fecha=?, Estado=? WHERE id_reservas=?";
    private static final String DELETE = "DELETE FROM reservas WHERE id_reservas=?";
    private Connection connection;
    public reservasDAO(){
        connection = ConnectionMariaDB.getConnection();
    }
    @Override
    public reservas save(reservas entity) {
        reservas result = entity;
        if (entity != null) {
            String Fecha = entity.getFecha();
            if (Fecha != null) {
                reservas isInDataBase = findByDate(Fecha);
                if (isInDataBase == null) {
                    try (PreparedStatement pst = connection.prepareStatement(INSERT)) {
                        pst.setString(1, entity.getFecha());
                        pst.setString(2, entity.getEstado());
                        pst.executeUpdate();
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else {
                    try (PreparedStatement pst = connection.prepareStatement(UPDATE)) {
                        pst.setString(1, entity.getFecha());
                        pst.setString(2, entity.getEstado());
                        pst.executeUpdate();
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public reservas delete(reservas entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = connection.prepareStatement(DELETE)) {
                pst.setInt(1, entity.getId_reserva());
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
    public reservas findById(String key) {
        return null;
    }

    @Override
    public List<reservas> findbyAll() {
        return null;
    }

    @Override
    public reservas findByDate(String key) {
        reservas result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYDATE)) {
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    reservas c = new reservas();
                    c.setFecha(res.getString("Fecha"));
                    c.setEstado(res.getString("Estado"));
                    result = c;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public reservas findByDNI(String key) {
        return null;
    }

    @Override
    public reservas findByName(String key) {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
