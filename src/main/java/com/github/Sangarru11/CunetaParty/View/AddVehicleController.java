package com.github.Sangarru11.CunetaParty.View;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddVehicleController extends Controller implements Initializable {
    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void ReturnToPrincipalPanel() throws IOException {
        MainController.changeScene(Scenes.PrinPanel, null);
    }
}
