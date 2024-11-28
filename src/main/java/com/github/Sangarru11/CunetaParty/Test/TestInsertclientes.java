package com.github.Sangarru11.CunetaParty.Test;

import com.github.Sangarru11.CunetaParty.model.DAO.clientesDAO;
import com.github.Sangarru11.CunetaParty.model.entity.clientes;

public class TestInsertclientes {
    public static void main(String[] args) {
        clientes clientes = new clientes();
        clientes.setId_cliente(1);
        clientes.setDni("12345678A");
        clientes.setNombre("Juan Perez");
        clientes.setTelefono("123456789");
        clientes.setModelo_vehiculo("Toyota Corolla");
        clientes.setMatricula("ABC1234");

        clientesDAO clientesDAO = new clientesDAO();
        clientesDAO.save(clientes);
    }
}
