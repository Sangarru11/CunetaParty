package com.github.Sangarru11.CunetaParty.model.entity;

import java.util.Objects;

public class servicios {
    private int id_servicio;
    private String nombre;
    private String precio;

    public servicios() {
    }
    public servicios(int id_servicio, String nombre, String precio) {
        this.id_servicio = id_servicio;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        servicios servicios = (servicios) o;
        return id_servicio == servicios.id_servicio && Objects.equals(nombre, servicios.nombre) && Objects.equals(precio, servicios.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_servicio, nombre, precio);
    }

    @Override
    public String toString() {
        return "servicios{" +
                "id_servicio=" + id_servicio +
                ", nombre='" + nombre + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }
}
