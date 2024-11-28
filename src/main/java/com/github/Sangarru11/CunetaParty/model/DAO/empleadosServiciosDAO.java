package com.github.Sangarru11.CunetaParty.model.DAO;

import com.github.Sangarru11.CunetaParty.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.CunetaParty.model.entity.empleados;
import com.github.Sangarru11.CunetaParty.model.entity.empleados_servicios;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class empleadosServiciosDAO implements DAO <empleados_servicios,String>{
    private static final String INSERTEMPLEADOSSERVICIOS = "INSERT INTO empleados_servicios (ID_empleado, ID_servicio VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM empleados_servicios WHERE id_empleado=?";
    private Connection connection;
    public empleadosServiciosDAO(){
        connection = ConnectionMariaDB.getConnection();
    }
    public void AsignarEmpleadoServicio(int ID_servicio, int ID_empleado){
        try(PreparedStatement pst = connection.prepareStatement(INSERTEMPLEADOSSERVICIOS)){
            pst.setInt(1, ID_servicio);
            pst.setInt(2, ID_empleado);
            pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public empleados_servicios save(empleados_servicios entity) {
        return null;
    }

    @Override
    public empleados_servicios delete(empleados_servicios entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = connection.prepareStatement(DELETE)) {
                pst.setInt(1, entity.getID_servicio());
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

    public void EliminarAsignacion(int ID_servicio, int ID_empleado) throws SQLException{
        try(PreparedStatement pst = connection.prepareStatement(DELETE)) {
            pst.setInt(1, ID_servicio);
            pst.setInt(2, ID_empleado);
            pst.executeUpdate();
        }
    }

    @Override
    public empleados_servicios findById(String key) {
        return null;
    }

    @Override
    public List<empleados_servicios> findbyAll() {
        return null;
    }

    @Override
    public empleados_servicios findByDate(String key) {
        return null;
    }

    @Override
    public empleados_servicios findByDNI(String key) {
        return null;
    }

    @Override
    public empleados_servicios findByName(String key) {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
