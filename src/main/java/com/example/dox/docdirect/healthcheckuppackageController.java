package com.example.dox.docdirect;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class healthcheckuppackageController {
        @FXML
        private Button buy1;

        @FXML
        private Button buy10;

        @FXML
        private Button buy11;

        @FXML
        private Button buy2;

        @FXML
        private Button buy3;

        @FXML
        private Button buy4;

        @FXML
        private Button buy5;

        @FXML
        private Button buy6;

        @FXML
        private Button buy7;

        @FXML
        private Button buy8;

        @FXML
        private Button buy9;

        @FXML
        private Button cart1;

        @FXML
        private Button cart10;

        @FXML
        private Button cart11;

        @FXML
        private Button cart2;

        @FXML
        private Button cart3;

        @FXML
        private Button cart4;

        @FXML
        private Button cart5;

        @FXML
        private Button cart6;

        @FXML
        private Button cart7;

        @FXML
        private Button cart8;

        @FXML
        private Button cart9;

        @FXML
        private Button about;

        @FXML
        private Button contact;

        @FXML
        private Button doctors;

        @FXML
        private Button home;

        @FXML
        private Button service;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void initialize() {
        home.setOnAction(event -> switchScene(event, "dashboard.fxml"));
        service.setOnAction(event -> switchScene(event, "services.fxml"));
        doctors.setOnAction(event -> switchScene(event, "DoctorListingPage.fxml"));
        about.setOnAction(event -> switchScene(event, "about.fxml"));
        contact.setOnAction(event -> switchScene(event, "contact.fxml"));

    }

    private void switchScene(javafx.event.ActionEvent event, String fxmlFile) {
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlFile));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }


