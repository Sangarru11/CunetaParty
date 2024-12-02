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

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static Stage stage;
    public static Controller currentController;

    @Override
    public void start(Stage stage) throws IOException {
        View view = MainController.loadFXML(Scenes.MAIN);
        scene = new Scene(view.scene, 640, 480);
        currentController = view.controller;
        currentController.onOpen(null);
        App.stage = stage;
        stage.setTitle("Cuneta Party");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/github/Sangarru11/CunetaParty/view/Imagenes/CunetaPartyLogo.png")));
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}