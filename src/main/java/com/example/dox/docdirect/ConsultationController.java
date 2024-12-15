package com.example.dox.docdirect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ConsultationController {

        @FXML
        private Button amr_jotno_plans;

        @FXML
        private Button book_appointment_btn1;

        @FXML
        private Button book_appointment_btn2;

        @FXML
        private Button book_appointment_btn3;

        @FXML
        private Button btn_about;

        @FXML
        private Button contact_btn;

        @FXML
        private Button home_btn;

        @FXML
        private Button our_doctor_btn;

        @FXML
        private Button services_btn;

    @FXML
    private Button mycart_btn;

    private Stage stage;
    private Scene scene;

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


    @FXML
    private void handleHome(ActionEvent event) {
        navigateToScene(event, "Myfxmls/dashboard/dashboard.fxml");
    }
    @FXML
    private void handleDoctors(ActionEvent event) {
        navigateToScene(event, "Myfxmls/Doctorss/DoctorListingPage.fxml");
    }

    @FXML
    private void handleAbout(ActionEvent event) {
        navigateToScene(event, "Myfxmls/About/about.fxml");
    }

    @FXML
    private void handleContact(ActionEvent event) {
        navigateToScene(event, "Myfxmls/contact/contact.fxml");
    }

    @FXML
    private void handleBookAppointment(ActionEvent event) {
        navigateToScene(event, "Myfxmls/Doctorss/DoctorListingPage.fxml");
    }

    @FXML
    private void handleOurServices(ActionEvent event) {
        navigateToScene(event, "Myfxmls/services/services.fxml");
    }
    @FXML
    private void handleAmrJotnoPlans(ActionEvent event) {
        navigateToScene(event, "Myfxmls/MembershipPlans/membershipPlan3.fxml");
    }
    @FXML
    private void handleMycart(ActionEvent event) {
        navigateToScene(event, "Myfxmls/myCart/viewCart.fxml");
    }

}
