package com.github.Sangarru11.CunetaParty.View;

import com.github.Sangarru11.CunetaParty.App;
import com.github.Sangarru11.CunetaParty.model.DAO.empleadosDAO;
import com.github.Sangarru11.CunetaParty.model.entity.empleados;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    public static View loadFXML(Scenes scenes) throws IOException {
        String url = scenes.getURL();
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent p = loader.load();
        Controller c = loader.getController();
        View view = new View();
        view.scene = p;
        view.controller = c;
        return view;
    }

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
    public void Login() throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        empleados empleados = empleadosDAO.build().findByName(username);

        if (empleados != null) {
            if (password.equals(empleados.getPassword())) {
                if (empleados.isAdmin()) {
                    changeScene(Scenes.AdminPanel, null);
                } else {
                    changeScene(Scenes.PrinPanel, null);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Contraseña inválida.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nombre de usuario inválido.");
            alert.show();
        }
    }
    public static void changeScene(Scenes scene, Object data) throws IOException {
        View view = MainController.loadFXML(scene);
        Scene _scene = new Scene(view.scene, 1075, 720);
        App.currentController = view.controller;
        App.currentController.onOpen(data);
        App.stage.setScene(_scene);
        App.stage.show();
    }
    public static void changeSceneRegister(Scenes scene, Object data) throws IOException {
        View view = MainController.loadFXML(scene);
        Scene _scene = new Scene(view.scene, 600, 400);
        App.currentController = view.controller;
        App.currentController.onOpen(data);
        App.stage.setScene(_scene);
        App.stage.show();
    }
    @FXML
    public void ReturnToUserLogin() throws IOException {
        changeSceneRegister(Scenes.MAIN, null);
    }
}
