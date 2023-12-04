package com.example.photoviewer_viewcontroller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUILauncher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUILauncher.class.getResource("View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 471, 518);
        stage.setTitle("My Photos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}