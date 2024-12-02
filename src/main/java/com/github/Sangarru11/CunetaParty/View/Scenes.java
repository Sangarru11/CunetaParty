package com.github.Sangarru11.CunetaParty.View;

public enum Scenes {
    REGISTERPANEL("View/Panels/RegisterPanel.fxml"),
    MAIN("View/Panels/main.fxml"),
    PrinPanel("View/Panels/PrincipalPanel.fxml"),
    AddCustomersPanel("View/Panels/AddCustomers.fxml"),
    MechanicInfo("View/Panels/MechanicInfo.fxml"),
    AssingRepairs("View/Panels/AssingRepairs.fxml"),
    SelectCustomer("View/Panels/SelectCustomer.fxml"),
    SelectMechanic("View/Panels/SelectMechanic.fxml"),
    CreateRepair("View/Panels/CreateRepair.fxml"),
    AdminController("View/Panels/AdminManager.fxml"),
    CustomersListController("View/Panels/CustomerList.fxml"),
    AdminPanel("View/Panels/AdminPanel.fxml");

    private String url;
    Scenes(String url){
        this.url=url;
    }
    public String getURL(){
        return url;
    }

}
