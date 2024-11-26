package com.github.Sangarru11.CunetaParty.model.entity;

import java.util.Objects;

public class empleados_servicios {
    private int ID_empleado;
    private int ID_servicio;

    public empleados_servicios() {
    }

    public empleados_servicios(int ID_empleado, int ID_servicio) {
        this.ID_empleado = ID_empleado;
        this.ID_servicio = ID_servicio;
    }

    public int getID_empleado() {
        return ID_empleado;
    }

    public void setID_empleado(int ID_empleado) {
        this.ID_empleado = ID_empleado;
    }

    public int getID_servicio() {
        return ID_servicio;
    }

    public void setID_servicio(int ID_servicio) {
        this.ID_servicio = ID_servicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        empleados_servicios that = (empleados_servicios) o;
        return ID_empleado == that.ID_empleado && ID_servicio == that.ID_servicio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID_empleado, ID_servicio);
    }

    @Override
    public String toString() {
        return "empleados_servicios{" +
                "ID_empleado=" + ID_empleado +
                ", ID_servicio=" + ID_servicio +
                '}';
    }
}
