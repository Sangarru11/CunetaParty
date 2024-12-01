package com.github.Sangarru11.CunetaParty.View;

public enum Scenes {

    MAIN("View/PantallasClientes/LoginClientes.fxml"),
    LoginEmpleados("View/PantallasEmpleados/Main.fxml"),
    AdminPanel("View/PantallasEmpleados/AdminPanel.fxml"),
    PrinPanel("View/PantallasClientes/PrincipalPanel.fxml"),
    SelectRepair("View/PantallasClientes/SelectRepair.fxml"),
    AddVehicle("View/PantallasClientes/AddVehicle.fxml"),
    ClientesRegisterPanel("View/PantallasClientes/PantallaRegistroClientes.fxml"),
    MecanicaUrgente("View/PantallasClientes/MecanicaUrgente.fxml"),
    MecanicaRapida("View/PantallasClientes/MecanicaRapida.fxml"),
    Reprogramaciones("View/PantallasClientes/Reprogramaciones.fxml"),
    Comprobacion("View/PantallasClientes/Comprobacion.fxml"),
    EmpleadosRegisterPanel("View/PantallasEmpleados/PantallaRegistroEmpleados.fxml");
    private String url;
    Scenes(String url){
        this.url=url;
    }
    public String getURL(){
        return url;
    }
}
