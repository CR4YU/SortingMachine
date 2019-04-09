package com.crayu.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FX extends Application {


    public static final String TITLE = "Sorting times";

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(TITLE);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/chart_ui.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void run(String[] args) {
        launch(args);
    }

}
