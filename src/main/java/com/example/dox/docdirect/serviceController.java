package com.example.dox.docdirect;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class serviceController {

    @FXML
    private Button About_btn;

    @FXML
    private Button btn_bookappointment;

    @FXML
    private Button btn_consultations;

    @FXML
    private Button btn_health_checkupnPackages;

    @FXML
    private Button btn_membershipPlans;

    @FXML
    private Button contact_btn;

    @FXML
    private Button home_btn;

    @FXML
    private Button ourDoctors_btn;

    @FXML
    private Button service_btn;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void initialize() {
        home_btn.setOnAction(event -> switchScene(event, "Myfxmls/dashboard/dashboard.fxml"));
        service_btn.setOnAction(event -> switchScene(event, "Myfxmls/services/services.fxml"));
        ourDoctors_btn.setOnAction(event -> switchScene(event, "Myfxmls/Doctorss/DoctorListingPage.fxml"));
        About_btn.setOnAction(event -> switchScene(event, "Myfxmls/About/about.fxml"));
        contact_btn.setOnAction(event -> switchScene(event, "Myfxmls/contact/contact.fxml"));
        btn_bookappointment.setOnAction(event -> switchScene(event, "Myfxmls/Doctorss/DoctorListingPage.fxml"));
        btn_consultations.setOnAction(event -> switchScene(event, "Myfxmls/consultation/consultation.fxml"));
        btn_health_checkupnPackages.setOnAction(event -> switchScene(event, "Myfxmls/HealthCheckup/healthCheckupPaackages.fxml"));
        btn_membershipPlans.setOnAction(event -> switchScene(event, "Myfxmls/MembershipPlans/membershipPlan.fxml"));
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
