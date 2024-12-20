package com.github.Sangarru11.CunetaParty.model.DAO;

import com.github.Sangarru11.CunetaParty.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.CunetaParty.model.entity.Employee;
import com.github.Sangarru11.CunetaParty.model.entity.RepairEmployee;
import com.github.Sangarru11.CunetaParty.model.entity.Repairs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RepairEmployeeDAO implements DAO<RepairEmployee,String> {
    private static final String INSERTEMPLOYEEREPAIRS = "INSERT INTO repair_employee (IdRepair, IdEmployee) VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM repair_employee WHERE IdEmployee=?";
    private Connection connection;

    public RepairEmployeeDAO() {
        connection = ConnectionMariaDB.getConnection();
    }
    /**
     * Asigna un empleado a una reparación en la base de datos.
     * @param IdRepair el ID de la reparación
     * @param IdEmployee el ID del empleado
     */
    public void assignEmployeeToRepair(int IdRepair, int IdEmployee) {
        try (PreparedStatement pst = connection.prepareStatement(INSERTEMPLOYEEREPAIRS)) {
            pst.setInt(1, IdRepair);
            pst.setInt(2, IdEmployee);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RepairEmployee save(RepairEmployee entity) {
        return null;
    }

    /**
     * Elimina una asignación de empleado a reparación de la base de datos.
     * @param entity la entidad RepairEmployee a eliminar
     * @return la entidad RepairEmployee eliminada
     * @throws SQLException si ocurre algún error de SQL
     */
    @Override
    public RepairEmployee delete(RepairEmployee entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = connection.prepareStatement(DELETE)) {
                pst.setInt(1, entity.getRepairId());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    /**
     * Elimina una asignación de empleado a reparación de la base de datos.
     * @param idEmployee el ID del empleado
     * @param idRepair el ID de la reparación
     * @throws SQLException si ocurre algún error de SQL
     */
    public void deleteAssignment(int idEmployee, int idRepair) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(DELETE)) {
            pst.setInt(1, idEmployee);
            pst.setInt(2, idRepair);
            pst.executeUpdate();
        }
    }
    @Override
    public Employee adminManage(Employee entity){
        return null;
    }

    @Override
    public Repairs findByPlateNumber(String key) {
        return null;
    }

    @Override
    public RepairEmployee findById(String key) {
        return null;
    }

    @Override
    public List<RepairEmployee> findbyAll() {
        return null;
    }

    @Override
    public RepairEmployee findByDate(String key) {
        return null;
    }

    @Override
    public RepairEmployee findByDNI(String key) {
        return null;
    }

    @Override
    public RepairEmployee findByName(String key) {
        return null;
    }


    @Override
    public void close() throws IOException {

    }
    public static RepairEmployeeDAO build(){
        return new RepairEmployeeDAO();
    }
}
