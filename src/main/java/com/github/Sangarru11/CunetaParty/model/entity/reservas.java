package com.github.Sangarru11.CunetaParty.model.entity;

import java.util.Objects;

public class reservas {
    private int id_reserva;
    private String fecha;
    private String estado;

    public reservas() {
    }

    public reservas(int id_reserva, String fecha, String estado) {
        this.id_reserva = id_reserva;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        reservas reservas = (reservas) o;
        return id_reserva == reservas.id_reserva && Objects.equals(fecha, reservas.fecha) && Objects.equals(estado, reservas.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_reserva, fecha, estado);
    }

    @Override
    public String toString() {
        return "reservas{" +
                "id_reserva=" + id_reserva +
                ", fecha='" + fecha + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
