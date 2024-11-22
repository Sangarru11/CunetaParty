package com.github.Sangarru11.CunetaParty.model.entity;

import java.util.Objects;

public class clientes {
    private int id_cliente;
    private String nombre;
    private String telefono;
    private String modelo_vehiculo;
    private String matricula;

    public clientes() {
    }

    public clientes(int id_cliente, String nombre, String telefono, String modelo_vehiculo, String matricula) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.modelo_vehiculo = modelo_vehiculo;
        this.matricula = matricula;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
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

    public String getModelo_vehiculo() {
        return modelo_vehiculo;
    }

    public void setModelo_vehiculo(String modelo_vehiculo) {
        this.modelo_vehiculo = modelo_vehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        clientes clientes = (clientes) o;
        return id_cliente == clientes.id_cliente && Objects.equals(nombre, clientes.nombre) && Objects.equals(telefono, clientes.telefono) && Objects.equals(modelo_vehiculo, clientes.modelo_vehiculo) && Objects.equals(matricula, clientes.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_cliente, nombre, telefono, modelo_vehiculo, matricula);
    }

    @Override
    public String toString() {
        return "clientes{" +
                "id_cliente=" + id_cliente +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", modelo_vehiculo='" + modelo_vehiculo + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
