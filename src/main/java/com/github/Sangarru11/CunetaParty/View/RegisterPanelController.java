package com.github.Sangarru11.CunetaParty.View;

import com.github.Sangarru11.CunetaParty.App;
import com.github.Sangarru11.CunetaParty.model.DAO.EmployeeDAO;
import com.github.Sangarru11.CunetaParty.model.entity.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterPanelController extends Controller implements Initializable {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtDNI;

    @Override
    public void onOpen(Object input) throws IOException {

    }
    /**
     * Método para cambiar la escena actual.
     * @param scene La nueva escena a la que se cambiará.
     * @param data Los datos que se pasarán a la nueva escena.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public static void changeScene(Scenes scene, Object data) throws IOException {
        View view = MainController.loadFXML(scene);
        Scene _scene = new Scene(view.scene, 640, 480);
        App.currentController = view.controller;
        App.currentController.onOpen(data);
        App.stage.setScene(_scene);
        App.stage.show();
    }

    @Override
    public void onClose(Object output) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Método para registrar un nuevo empleado.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    private void Register() throws IOException {
        Employee newEmployee = new Employee();
        String name = txtUsername.getText();
        newEmployee.setName(name);
        String password = txtPassword.getText();
        newEmployee.setPassword(password);
        String DNI = txtDNI.getText();
        newEmployee.setDNI(DNI);

        Employee employee = EmployeeDAO.build().findByName(name);
        if (employee != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El empleado ya existe en la base de datos");
            alert.show();
            return;
        }

        EmployeeDAO.build().save(newEmployee);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("El empleado se ha añadido correctamente");
        alert.show();
    }

    /**
     * Método para cambiar a la escena de inicio de sesión.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    public void ReturnToLogin() throws IOException {
        changeScene(Scenes.MAIN, null);
    }
}
