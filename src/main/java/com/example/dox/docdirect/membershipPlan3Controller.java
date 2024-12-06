package com.example.dox.docdirect;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class membershipPlan3Controller {

    @FXML
    private Button btn_about;

    @FXML
    private Button btn_bookAppointment;

    @FXML
    private Button btn_buy1;

    @FXML
    private Button btn_buy2;

    @FXML
    private Button btn_buy3;

    @FXML
    private Button btn_cart1;

    @FXML
    private Button btn_cart2;

    @FXML
    private Button btn_cart3;

    @FXML
    private Button btn_contact;

    @FXML
    private Button btn_doctor;

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_service;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private void initialize() {
        btn_home.setOnAction(event -> switchScene(event, "dashboard.fxml"));
        btn_service.setOnAction(event -> switchScene(event, "services.fxml"));
        btn_doctor.setOnAction(event -> switchScene(event, "DoctorListingPage.fxml"));
        btn_about.setOnAction(event -> switchScene(event, "about.fxml"));
        btn_contact.setOnAction(event -> switchScene(event, "contact.fxml"));
        btn_bookAppointment.setOnAction(event -> switchScene(event, "DoctorListingPage.fxml"));
//        btn_consultations.setOnAction(event -> switchScene(event, "consultation.fxml"));
//        btn_health_checkupnPackages.setOnAction(event -> switchScene(event, "healthCheckupPaackages.fxml"));
//        btn_membershipPlans.setOnAction(event -> switchScene(event, "membershipPlan.fxml"));
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

