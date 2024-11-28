package com.github.Sangarru11.CunetaParty.model.DAO;

import com.github.Sangarru11.CunetaParty.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.CunetaParty.model.entity.empleados;
import com.github.Sangarru11.CunetaParty.model.entity.servicios;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class serviciosDAO implements DAO<servicios,String>{
    private static final String FINDBYID = "SELECT s.id_servicio, s.Nombre, s.Precio, e.id_empleado, e.DNI, e.Nombre AS nombre_empleado\n" + "FROM servicios AS s\n" + "LEFT JOIN empleados_servicios es ON s.id_servicio = es.ID_servicio\n" + "LEFT JOIN empleados e ON es.ID_empleado = e.id_empleado\n" + "WHERE s.id_servicio = ?;\n";
    private static final String FINDBYNAME = "SELECT s.id_servicio, s.Nombre, s.Precio, e.id_empleado, e.DNI, e.Nombre AS nombre_empleado\n" + "FROM servicios AS s\n" +"LEFT JOIN empleados_servicios es ON s.id_servicio = es.ID_servicio\n" + "LEFT JOIN empleados e ON es.ID_empleado = e.id_empleado\n" + "WHERE s.Nombre = ?;\n";
    private static final String FINDBYALL = "SELECT s.id_servicio, s.Nombre, s.Precio, e.id_empleado, e.DNI, e.Nombre AS nombre_empleado " + "FROM servicios AS s " + "LEFT JOIN empleados_servicios es ON s.id_servicio = es.ID_servicio " + "LEFT JOIN empleados e ON es.ID_empleado = e.id_empleado";
    private static final String INSERT = "INSERT INTO servicios (Nombre, Precio) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE servicios SET Nombre=?, Precio=? WHERE id_empleado=?";
    private static final String DELETE = "DELETE FROM servicios WHERE id_empleado=?";
    private Connection connection;
    public serviciosDAO(){
        connection = ConnectionMariaDB.getConnection();
    }
    @Override
    public servicios save(servicios entity) {
        servicios result = entity;
        if (entity != null) {
            String Nombre = entity.getNombre();
            if (Nombre != null) {
                servicios isInDataBase = findByName(Nombre);
                if (isInDataBase == null) {
                    try (PreparedStatement pst = connection.prepareStatement(INSERT)) {
                        pst.setString(1, entity.getNombre());
                        pst.setString(2, entity.getPrecio());
                        pst.executeUpdate();
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else {
                    try (PreparedStatement pst = connection.prepareStatement(UPDATE)) {
                        pst.setString(1, entity.getNombre());
                        pst.setString(2, entity.getPrecio());
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
    public servicios delete(servicios entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = connection.prepareStatement(DELETE)) {
                pst.setInt(1, entity.getId_servicio());
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
    public servicios findById(String key) {
        servicios result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYID)) {
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    ServiciosLazy s = new ServiciosLazy();
                    s.setId_servicio(res.getInt("ID"));
                    s.setNombre(res.getString("Nombre"));
                    s.setPrecio(res.getString("Precio"));

                    List<empleados> empleadosList = new ArrayList<>();
                    do {
                        empleados e = new empleados();
                        e.setId_empleado(res.getInt("ID"));
                        e.setDNI(res.getString("DNI"));
                        e.setNombre(res.getString("Nombre"));
                        empleadosList.add(e);
                    } while (res.next());

                    s.setEmpleados(empleadosList);
                    result = s;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<servicios> findbyAll() {
        List<servicios> serviciosList = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement(FINDBYALL)){
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    ServiciosLazy s = new ServiciosLazy();
                    s.setId_servicio(res.getInt("ID"));
                    s.setNombre(res.getString("Nombre"));
                    s.setPrecio(res.getString("Precio"));

                    List<empleados> empleadosList = new ArrayList<>();
                    do {
                        empleados e = new empleados();
                        e.setId_empleado(res.getInt("ID"));
                        e.setDNI(res.getString("DNI"));
                        e.setNombre(res.getString("Nombre"));
                        empleadosList.add(e);
                    } while (res.next() && res.getInt("ID") == s.getId_servicio());

                    s.setEmpleados(empleadosList);
                    serviciosList.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviciosList;
    }

    @Override
    public servicios findByDate(String key) {
        return null;
    }

    @Override
    public servicios findByDNI(String key) {
        return null;
    }

    @Override
    public servicios findByName(String key) {
        servicios result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYNAME)) {
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    ServiciosLazy s = new ServiciosLazy();
                    s.setId_servicio(res.getInt("ID"));
                    s.setNombre(res.getString("Nombre"));
                    s.setPrecio(res.getString("Precio"));

                    List<empleados> empleadosList = new ArrayList<>();
                    do {
                        empleados e = new empleados();
                        e.setId_empleado(res.getInt("ID"));
                        e.setDNI(res.getString("DNI"));
                        e.setNombre(res.getString("Nombre"));
                        empleadosList.add(e);
                    } while (res.next());

                    s.setEmpleados(empleadosList);
                    result = s;
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
    class ServiciosLazy extends servicios {
        private static final String FINDEMPLOYEESBYREPAIR = "SELECT e.* FROM servicios s,empleados_servicios se, empleados e WHERE s.id_servicio=se.ID_servicio AND se.ID_empleado=e.id_empleado AND s.id_servicio=?";

        public ServiciosLazy() {

        }

        public ServiciosLazy(int id_servicio, List<empleados> empleados, String nombre, String precio) {
            super(id_servicio, empleados, nombre, precio);
        }

        @Override
        public List<empleados> getEmpleados() {
            if (super.getEmpleados() == null) {
                Connection connection = ConnectionMariaDB.getConnection();
                List<empleados> result = new ArrayList<>();
                try (PreparedStatement pst = connection.prepareStatement(FINDEMPLOYEESBYREPAIR)) {
                    pst.setInt(1, getId_servicio());
                    try (ResultSet res = pst.executeQuery()) {
                        while (res.next()) {
                            empleados e = new empleados();
                            e.setId_empleado(res.getInt("ID"));
                            e.setDNI(res.getString("DNI"));
                            e.setNombre(res.getString("Nombre"));
                            result.add(e);
                        }
                    }
                    super.setEmpleados(result);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return super.getEmpleados();
        }
    }
}