package com.github.Sangarru11.CunetaParty.model.entity;

import java.util.List;
import java.util.Objects;

public class empleados {
    private int id_empleado;
    private List<servicios> servicios;
    private String nombre;
    private String DNI;
    private String Especialidad;
    private String password;
    private boolean isAdmin;

    public empleados() {
    }

    public empleados(int id_empleado, List<servicios> servicios, String nombre, String DNI, String especialidad, String password, boolean isAdmin) {
        this.id_empleado = id_empleado;
        this.servicios = servicios;
        this.nombre = nombre;
        this.DNI = DNI;
        Especialidad = especialidad;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public List<servicios> getServicios() {
        return servicios;
    }

    public void setServicios(List<servicios> servicios) {
        this.servicios = servicios;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String especialidad) {
        Especialidad = especialidad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        empleados empleados = (empleados) o;
        return id_empleado == empleados.id_empleado && isAdmin == empleados.isAdmin && Objects.equals(servicios, empleados.servicios) && Objects.equals(nombre, empleados.nombre) && Objects.equals(DNI, empleados.DNI) && Objects.equals(Especialidad, empleados.Especialidad) && Objects.equals(password, empleados.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_empleado, servicios, nombre, DNI, Especialidad, password, isAdmin);
    }

    @Override
    public String toString() {
        return "empleados{" +
                "id_empleado=" + id_empleado +
                ", servicios=" + servicios +
                ", nombre='" + nombre + '\'' +
                ", DNI='" + DNI + '\'' +
                ", Especialidad='" + Especialidad + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
