package com.github.Sangarru11.CunetaParty.View;

import com.github.Sangarru11.CunetaParty.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalPanelController extends Controller implements Initializable {

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public static void changeSceneLogin(Scenes scene, Object data) throws IOException {
        View view = MainController.loadFXML(scene);
        Scene _scene = new Scene(view.scene, 600, 400);
        App.currentController = view.controller;
        App.currentController.onOpen(data);
        App.stage.setScene(_scene);
        App.stage.show();
    }
    @FXML
    public void ReturnToUserLogin() throws IOException {
        changeSceneLogin(Scenes.MAIN, null);
    }
    @FXML
    public void AddVehicle() throws IOException {
        MainController.changeScene(Scenes.AddVehicle, null);
    }
    @FXML
    public void SelectService() throws IOException {
        MainController.changeScene(Scenes.SelectRepair, null);
    }
}
