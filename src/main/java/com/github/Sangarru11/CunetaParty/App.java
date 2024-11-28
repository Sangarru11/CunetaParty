package com.github.Sangarru11.CunetaParty;

import com.github.Sangarru11.CunetaParty.View.Controller;
import com.github.Sangarru11.CunetaParty.View.MainController;
import com.github.Sangarru11.CunetaParty.View.Scenes;
import com.github.Sangarru11.CunetaParty.View.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static Scene scene;
    public static Stage stage;
    public static Controller currentController;

    @Override
    public void start(Stage stage) throws IOException {
        View view = MainController.loadFXML(Scenes.MAIN);
        scene = new Scene(view.scene, 600, 400);
        currentController = view.controller;
        currentController.onOpen(null);
        App.stage = stage;
        stage.setTitle("CunetaParty");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/github/Sangarru11/CunetaParty/View/Imagenes/CunetaPartyLogo.png")));
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}
