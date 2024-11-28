package com.github.Sangarru11.CunetaParty.View;

import com.github.Sangarru11.CunetaParty.App;
import com.github.Sangarru11.CunetaParty.model.DAO.clientesDAO;
import com.github.Sangarru11.CunetaParty.model.entity.clientes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UsersRegisterController extends Controller implements Initializable {
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtTelefono;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtDNI;
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
    private void Register() throws IOException {
        clientes nuevoCliente = new clientes();
        String name = txtUsuario.getText();
        nuevoCliente.setNombre(name);
        String password = txtPassword.getText();
        nuevoCliente.setPassword(password);
        String DNI = txtDNI.getText();
        nuevoCliente.setDni(DNI);
        String Telefono = txtTelefono.getText();
        nuevoCliente.setTelefono(Telefono);

        clientes employee = clientesDAO.build().findByDNI(DNI);
        if (employee != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El usuario ya existe en la base de datos");
            alert.show();
            return;
        }

        clientesDAO.build().save(nuevoCliente);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("El usuario se ha añadido correctamente");
        alert.show();
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
    public void ReturnToLogin() throws IOException{
        changeSceneRegister(Scenes.MAIN, null);
    }
}
