package com.github.Sangarru11.CunetaParty.Test;

import com.github.Sangarru11.CunetaParty.model.DAO.clientesDAO;
import com.github.Sangarru11.CunetaParty.model.DAO.empleadosDAO;
import com.github.Sangarru11.CunetaParty.model.entity.clientes;
import com.github.Sangarru11.CunetaParty.model.entity.empleados;

public class TestInsertEmpleados {
    public static void main(String[] args) {
        empleados empleados = new empleados();
        empleados.setNombre("Santiago");
        empleados.setDNI("31028046B");
        empleados.setEspecialidad("Cambios de aceite");
        empleados.setPassword("1234");

        empleadosDAO empleadosDAO = new empleadosDAO();
        empleadosDAO.save(empleados);
    }
}
