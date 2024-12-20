package com.github.Sangarru11.CunetaParty.model.DAO;

import com.github.Sangarru11.CunetaParty.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.CunetaParty.model.entity.Customers;
import com.github.Sangarru11.CunetaParty.model.entity.Employee;
import com.github.Sangarru11.CunetaParty.model.entity.Repairs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersDAO implements DAO<Customers,String> {
    // Consulta para buscar un cliente por su DNI
    // Se unen las tablas "customers" y "repairs" usando el campo "plateNumber".
    // Solo se devuelven los clientes que tienen reparaciones asociadas a ellos.
    // El resultado está agrupado por el ID del cliente y sus datos, y se ordena por el nombre del cliente.
    private static final String FINDBYDNI =
            "SELECT c.idCustomer, c.dni, c.name, c.phoneNumber, c.plateNumber, COUNT(r.IdRepair) AS totalRepairs " +
                    "FROM customers AS c " +
                    "JOIN repairs AS r ON c.plateNumber = r.plateNumber " +
                    "WHERE c.dni = ? " +  // Filtro por DNI del cliente
                    "GROUP BY c.idCustomer, c.dni, c.name, c.phoneNumber, c.plateNumber " +  // Agrupación por los campos del cliente
                    "ORDER BY c.name";  // Ordena los resultados por el nombre del cliente

    // Consulta para buscar un cliente por su ID
    // Al igual que la consulta anterior, se realiza un JOIN entre "customers" y "repairs" por el campo "plateNumber".
    // Se filtra por el ID del cliente, y solo se incluirán aquellos clientes con reparaciones asociadas.
    // Los resultados están agrupados por los campos del cliente y ordenados por el nombre.
    private static final String FINDBYID =
            "SELECT c.idCustomer, c.dni, c.name, c.phoneNumber, c.plateNumber, COUNT(r.IdRepair) AS totalRepairs " +
                    "FROM customers AS c " +
                    "JOIN repairs AS r ON c.plateNumber = r.plateNumber " +
                    "WHERE c.idCustomer = ? " +  // Filtro por ID del cliente
                    "GROUP BY c.idCustomer, c.dni, c.name, c.phoneNumber, c.plateNumber " +  // Agrupación por los campos del cliente
                    "ORDER BY c.name";  // Ordena los resultados por el nombre del cliente

    // Consulta para buscar un cliente por su nombre
    // Similar a las anteriores, se realiza un JOIN con la tabla "repairs" utilizando el campo "plateNumber".
    // La consulta filtra por nombre y solo devuelve clientes con reparaciones asociadas a ellos.
    // Los resultados se agrupan por el ID del cliente y sus datos, y se ordenan por el nombre.
    private static final String FINDBYNAME =
            "SELECT c.idCustomer, c.dni, c.name, c.phoneNumber, c.plateNumber, COUNT(r.IdRepair) AS totalRepairs " +
                    "FROM customers AS c " +
                    "JOIN repairs AS r ON c.plateNumber = r.plateNumber " +
                    "WHERE c.name = ? " +  // Filtro por nombre del cliente
                    "GROUP BY c.idCustomer, c.dni, c.name, c.phoneNumber, c.plateNumber " +  // Agrupación por los campos del cliente
                    "ORDER BY c.name";  // Ordena los resultados por el nombre del cliente

    private static final String INSERT = "INSERT INTO customers (dni, name, phoneNumber, plateNumber) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE customers SET name=? WHERE idCustomer=?";
    private static final String DELETE = "DELETE FROM customers WHERE idCustomer=?";

    private Connection connection;

    public CustomersDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    /**
     * Añade un nuevo cliente a la base de datos o actualiza su nombre si ya existe.
     * @param entity el cliente a guardar o actualizar
     * @return el cliente guardado o actualizado
     */
    @Override
    public Customers save(Customers entity) {
        Customers result = entity;
        if (entity != null) {
            String dni = entity.getDNI();
            if (dni != null) {
                Customers isInDataBase = findByDNI(dni);
                if (isInDataBase == null) {
                    try (PreparedStatement pst = connection.prepareStatement(INSERT)) {
                        pst.setString(1, dni);
                        pst.setString(2, entity.getName());
                        pst.setString(3, entity.getPhoneNumber());
                        pst.setString(4, entity.getPlateNumber());
                        pst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement pst = connection.prepareStatement(UPDATE)) {
                        pst.setString(1, entity.getName());
                        pst.setInt(2, entity.getIdCustomer());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    /**
     * Elimina un cliente de la base de datos.
     * @param entity el cliente a eliminar
     * @return el cliente eliminado
     */
    @Override
    public Customers delete(Customers entity) {
        if (entity != null) {
            try (PreparedStatement pst = connection.prepareStatement(DELETE)) {
                pst.setInt(1, entity.getIdCustomer());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }
    @Override
    public Employee adminManage(Employee entity) throws SQLException {
        return null;
    }
    @Override
    public Repairs findByPlateNumber(String key) {
        return null;
    }

    /**
     * Busca un cliente por su ID.
     * @param key el ID del cliente a buscar
     * @return el cliente encontrado o null si no se encuentra
     */
    @Override
    public Customers findById(String key) {
        Customers result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYID)) {
            pst.setString(1, key);
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    Customers c = new Customers();
                    c.setIdCustomer(res.getInt("idCustomer"));
                    c.setDNI(res.getString("dni"));
                    c.setName(res.getString("name"));
                    c.setPhoneNumber(res.getString("phoneNumber"));
                    c.setPlateNumber(res.getString("plateNumber"));
                    result = c;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Busca todos los clientes en la base de datos.
     * @return una lista de todos los clientes encontrados
     */
    @Override
    public List<Customers> findbyAll() {
        List<Customers> result = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM customers")){
            try (ResultSet res = pst.executeQuery()){
                while (res.next()){
                    Customers c = new Customers();
                    c.setIdCustomer(res.getInt("idCustomer"));
                    c.setDNI(res.getString("DNI"));
                    c.setName(res.getString("Name"));
                    c.setPhoneNumber(res.getString("PhoneNumber"));
                    c.setPlateNumber(res.getString("PlateNumber"));
                    result.add(c);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Customers findByDate(String key) {
        return null;
    }

    /**
     * Busca un cliente por su número de DNI.
     * @param key el número de DNI del cliente a buscar
     * @return el cliente encontrado o null si no se encuentra
     */
    @Override
    public Customers findByDNI(String key) {
        Customers result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYDNI)) {
            pst.setString(1, key);
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    Customers c = new Customers();
                    c.setIdCustomer(res.getInt("idCustomer"));
                    c.setDNI(res.getString("dni"));
                    c.setName(res.getString("name"));
                    c.setPhoneNumber(res.getString("phoneNumber"));
                    c.setPlateNumber(res.getString("plateNumber"));
                    result = c;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Busca un cliente por su nombre.
     * @param key el nombre del cliente a buscar
     * @return el cliente encontrado o null si no se encuentra
     */
    @Override
    public Customers findByName(String key) {
        Customers result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYNAME)) {
            pst.setString(1, key);
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    Customers c = new Customers();
                    c.setIdCustomer(res.getInt("idCustomer"));
                    c.setDNI(res.getString("dni"));
                    c.setName(res.getString("name"));
                    c.setPhoneNumber(res.getString("phoneNumber"));
                    c.setPlateNumber(res.getString("plateNumber"));
                    result = c;
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

    public static CustomersDAO build() {
        return new CustomersDAO();
    }
}