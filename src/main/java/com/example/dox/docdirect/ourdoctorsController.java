package com.example.dox.docdirect;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ourdoctorsController {

    @FXML
    private Button homeButton;

    @FXML
    private Button ourDoctorsButton;

    @FXML
    private Button servicesButton;

    @FXML
    private Button aboutButton;

    @FXML
    private Button contactButton;

    @FXML
    private Button viewDetailsDoctor1;

    @FXML
    private Button viewDetailsDoctor2;

    @FXML
    private Button viewDetailsDoctor3;

    @FXML
    private Button viewDetailsDoctor4;

    // Utility method to switch scenes
    private void changeScene(String fxmlFileName) {
        try {
            // Get the current stage from any button in the current scene
            Stage stage = (Stage) homeButton.getScene().getWindow();

            // Load the new FXML file
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));

            // Set the new scene to the stage
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to load scene " + fxmlFileName);
        }
    }

    @FXML
    private void initialize() {
        // Set up event handlers for navigation buttons
        homeButton.setOnAction(event -> changeScene("Myfxmls/dashboard/dashboard.fxml"));
        ourDoctorsButton.setOnAction(event -> changeScene("Myfxmls/Doctorss/ourDoctors.fxml"));
        servicesButton.setOnAction(event -> changeScene("Myfxmls/services/services.fxml"));
        aboutButton.setOnAction(event -> changeScene("Myfxmls/About/about.fxml"));
        contactButton.setOnAction(event -> changeScene("Myfxmls/contact/contact.fxml"));

        // Set up event handlers for doctor details buttons
//        viewDetailsDoctor1.setOnAction(event -> changeScene("doctor1_details.fxml"));
//        viewDetailsDoctor2.setOnAction(event -> changeScene("doctor2_details.fxml"));
//        viewDetailsDoctor3.setOnAction(event -> changeScene("doctor3_details.fxml"));
//        viewDetailsDoctor4.setOnAction(event -> changeScene("doctor4_details.fxml"));
    }
}
