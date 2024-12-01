package com.example.dox.docdirect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutController {

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_services;

    @FXML
    private Button btn_doctors;

    @FXML
    private Button btn_about;

    @FXML
    private Button btn_contact;

    @FXML
    private Button btn_bookAppointment;

    @FXML
    private Button btn_ourServices;

    private Stage stage;
    private Scene scene;

    /**
     * Handles navigation between different scenes.
     *
     * @param event The button click event
     * @param fxmlFile The FXML file for the new scene
     */
    private void navigateToScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Action handlers for buttons to change scenes.
     */
    @FXML
    private void handleHome(ActionEvent event) {
        navigateToScene(event, "dashboard.fxml");
    }

    @FXML
    private void handleServices(ActionEvent event) {
        navigateToScene(event, "services.fxml");
    }

    @FXML
    private void handleDoctors(ActionEvent event) {
        navigateToScene(event, "ourDoctors.fxml");
    }

    @FXML
    private void handleAbout(ActionEvent event) {
        navigateToScene(event, "about.fxml");
    }

    @FXML
    private void handleContact(ActionEvent event) {
        navigateToScene(event, "contact.fxml");
    }

    @FXML
    private void handleBookAppointment(ActionEvent event) {
        navigateToScene(event, "ourDoctors.fxml");
    }

    @FXML
    private void handleOurServices(ActionEvent event) {
        navigateToScene(event, "services.fxml");
    }

    /**
     * Provides hover effect for better UX.
     */
    @FXML
    private void initialize() {
        applyHoverEffect(btn_home);
        applyHoverEffect(btn_services);
        applyHoverEffect(btn_doctors);
        applyHoverEffect(btn_about);
        applyHoverEffect(btn_contact);
        applyHoverEffect(btn_bookAppointment);
        applyHoverEffect(btn_ourServices);
    }

    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #EC407A; -fx-font-weight: bold; -fx-background-radius: 20;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #EC407A; -fx-text-fill: #FFFFFF; -fx-font-weight: bold; -fx-background-radius: 20;"));
    }
}
