package com.github.Sangarru11.CunetaParty.model.entity;

import java.util.Objects;

public class vehiculo {
    private int id;
    private String modeloVehiculo;
    private String matriculaVehiculo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        vehiculo vehiculo = (vehiculo) o;
        return id == vehiculo.id && Objects.equals(modeloVehiculo, vehiculo.modeloVehiculo) && Objects.equals(matriculaVehiculo, vehiculo.matriculaVehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modeloVehiculo, matriculaVehiculo);
    }

    @Override
    public String toString() {
        return "vehiculo{" +
                "id=" + id +
                ", modeloVehiculo='" + modeloVehiculo + '\'' +
                ", matriculaVehiculo='" + matriculaVehiculo + '\'' +
                '}';
    }
}