package com.github.Sangarru11.CunetaParty.model.entity;

import java.util.Objects;

public class clientes {
    private int id_cliente;
    private int id_vehiculo;
    private String dni;
    private String nombre;
    private String telefono;
    private String password;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        clientes clientes = (clientes) o;
        return id_cliente == clientes.id_cliente && id_vehiculo == clientes.id_vehiculo && Objects.equals(dni, clientes.dni) && Objects.equals(nombre, clientes.nombre) && Objects.equals(telefono, clientes.telefono) && Objects.equals(password, clientes.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_cliente, id_vehiculo, dni, nombre, telefono, password);
    }

    @Override
    public String toString() {
        return "clientes{" +
                "id_cliente=" + id_cliente +
                ", id_vehiculo=" + id_vehiculo +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
