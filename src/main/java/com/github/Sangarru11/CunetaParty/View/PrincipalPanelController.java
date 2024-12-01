package com.github.Sangarru11.CunetaParty.View;

import com.github.Sangarru11.CunetaParty.App;
import com.github.Sangarru11.CunetaParty.model.DAO.vehiculoDAO;
import com.github.Sangarru11.CunetaParty.model.entity.vehiculo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PrincipalPanelController extends Controller implements Initializable {
    @FXML
    private TableView<vehiculo> tableView;
    @FXML
    private TableColumn<vehiculo, String> ModeloVehiculo;
    @FXML
    private TableColumn<vehiculo, String> Matricula;
    @FXML
    private TableColumn<vehiculo, String> EstadoReparacion;
    private ObservableList<vehiculo> vehiculos;


    @Override
    public void onOpen(Object input) throws IOException {
        List<vehiculo> vehiculos = vehiculoDAO.build().findbyAll();
        this.vehiculos = FXCollections.observableArrayList(vehiculos);
        tableView.setItems(this.vehiculos);
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setEditable(true);

        tableView.setRowFactory(tv -> {
            TableRow<vehiculo> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                vehiculo vehiculos = tableView.getSelectionModel().getSelectedItem();
                if (event.getClickCount() == 3 && (!row.isEmpty())) {
                    vehiculo repairs = row.getItem();
                    try {
                        MainController.changeScene(Scenes.SelectRepair, vehiculos.getMatriculaVehiculo());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });

        ModeloVehiculo.setCellValueFactory(repairs -> new SimpleStringProperty(repairs.getValue().getModeloVehiculo()));
        ModeloVehiculo.setCellFactory(TextFieldTableCell.forTableColumn());

        Matricula.setCellValueFactory(repairs -> new SimpleStringProperty(repairs.getValue().getMatriculaVehiculo()));
        Matricula.setCellFactory(TextFieldTableCell.forTableColumn());
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

}
