package com.example.dox.docdirect;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button homeButton;

    @FXML
    private Button servicesButton;

    @FXML
    private Button contactButton;

    @FXML
    private Button aboutButton;

    @FXML
    private Button ourDoctorsButton;

    @FXML
    private Button bookAppointmentButton;

    private void changeScene(String fxmlFileName) {
        try {
            // Get the current stage from any button (all are part of the same scene)
            Stage stage = (Stage) homeButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        // Set button actions
        homeButton.setOnAction(event -> {
            // Stay on the same page, no action needed
        });

        servicesButton.setOnAction(event -> {
            changeScene("service.fxml");
        });

        contactButton.setOnAction(event -> {
            changeScene("contact.fxml");
        });

        aboutButton.setOnAction(event -> {
            changeScene("about.fxml");
        });

        ourDoctorsButton.setOnAction(event -> {
            changeScene("our_doctors.fxml");
        });

        bookAppointmentButton.setOnAction(event -> {
            changeScene("our_doctors.fxml");
        });
    }
}
