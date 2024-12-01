package com.github.Sangarru11.CunetaParty.View;

import com.github.Sangarru11.CunetaParty.model.DAO.clientesDAO;
import com.github.Sangarru11.CunetaParty.model.DAO.vehiculoDAO;
import com.github.Sangarru11.CunetaParty.model.entity.vehiculo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddVehicleController extends Controller implements Initializable {
    @FXML
    private TextField txtModeloVehiculo;
    @FXML
    private TextField txtMatricula;
    @FXML
    private ComboBox txtClienteId;

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
    private void AddVehicle() throws IOException {
        vehiculo nuevoVehiculo = new vehiculo();
        String VehicleModel = txtModeloVehiculo.getText();
        nuevoVehiculo.setModeloVehiculo(VehicleModel);
        String Matricula = txtMatricula.getText();
        nuevoVehiculo.setMatriculaVehiculo(Matricula);

        vehiculo vehiculo = vehiculoDAO.build().findByDNI(Matricula);
        if (vehiculo != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El vehiculo ya existe en la base de datos");
            alert.show();
            return;
        }

        vehiculoDAO.build().save(nuevoVehiculo);

        String clienteId = (String) txtClienteId.getValue();
        if (clienteId != null && !clienteId.isEmpty()) {
            try {
                int clienteIdInt = Integer.parseInt(clienteId);
                clientesDAO clienteDao = clientesDAO.build();
                clienteDao.assignVehicleToClient(clienteIdInt, nuevoVehiculo.getId());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("ID de cliente no válido.");
                alert.show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("El vehiculo se ha añadido correctamente");
        alert.show();
        MainController.changeScene(Scenes.PrinPanel, null);
    }

    @FXML
    public void ReturnToPrincipalPanel() throws IOException {
        MainController.changeScene(Scenes.PrinPanel, null);
    }
}
