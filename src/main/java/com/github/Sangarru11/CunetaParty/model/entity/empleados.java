package com.github.Sangarru11.CunetaParty.model.entity;

import java.util.Objects;

public class empleados {
    private int id_empleado;
    private String nombre;
    private String DNI;
    private String Especialidad;

    public empleados() {
    }

    public empleados(int id_empleado, String nombre, String DNI, String especialidad) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.DNI = DNI;
        Especialidad = especialidad;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        empleados empleados = (empleados) o;
        return id_empleado == empleados.id_empleado && Objects.equals(nombre, empleados.nombre) && Objects.equals(DNI, empleados.DNI) && Objects.equals(Especialidad, empleados.Especialidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_empleado, nombre, DNI, Especialidad);
    }

    @Override
    public String toString() {
        return "empleados{" +
                "id_empleado=" + id_empleado +
                ", nombre='" + nombre + '\'' +
                ", DNI='" + DNI + '\'' +
                ", Especialidad='" + Especialidad + '\'' +
                '}';
    }
}
