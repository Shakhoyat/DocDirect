package com.example.dox.docdirect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Myfxmls/login/login2.fxml"));
      //  Parent root = FXMLLoader.load(getClass().getResource("DoctorListingPage.fxml"));

        primaryStage.setTitle("DocDirect");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        // ...
    }

    public static void main(String[] args) {
        launch(args);
    }

}

