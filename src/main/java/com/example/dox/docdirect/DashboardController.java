package com.example.dox.docdirect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button btn_cart;

    @FXML
    private Button btn_logout;

    @FXML
    private Button aboutButton;

    @FXML
    private Button bookAppointmentButton;

    @FXML
    private Button contactbutton;

    @FXML
    private Button homeButton;

    @FXML
    private Button ourDoctorsButton;

    @FXML
    private Button servicesButton1;

    @FXML
    private Button servicesButton2;

    @FXML
    private void initialize() {

        btn_cart.setOnAction(event->switchScene("Myfxmls/myCart/viewCart.fxml","My Cart"));
        // Button action for "Services"
        servicesButton1.setOnAction(event -> switchScene("Myfxmls/services/services.fxml", "Services"));

        // Button action for "Our Doctors"
        ourDoctorsButton.setOnAction(event -> switchScene("Myfxmls/Doctorss/DoctorListingPage.fxml", "Our Doctors"));

        // Button action for "About"
        aboutButton.setOnAction(event -> switchScene("Myfxmls/About/about.fxml", "About"));

        // Button action for "Contact"
        contactbutton.setOnAction(event -> switchScene("Myfxmls/contact/contact.fxml", "Contact"));

        // Button action for "Explore Our Services"
        servicesButton2.setOnAction(event -> switchScene("Myfxmls/services/services.fxml", "Explore Our Services"));

        // Button action for "Book an Appointment"
        bookAppointmentButton.setOnAction(event -> switchScene("Myfxmls/Doctorss/DoctorListingPage.fxml", "Book an Appointment"));

        btn_logout.setOnAction(event -> {
            // Show logout success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Logout");
            alert.setHeaderText(null);
            alert.setContentText("Logout successful!");
            alert.showAndWait();
            switchScene("Myfxmls/login/login2.fxml", "Login");

        });
    }

    private void switchScene(String fxmlFileName, String title) {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            AnchorPane root = fxmlLoader.load();

            // Get the current stage
            Stage stage = (Stage) homeButton.getScene().getWindow();

            // Set the new scene
            stage.setScene(new Scene(root));
            stage.setTitle(title);

            // Confirmation message
            System.out.println(title + " scene loaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not load the " + title + " scene.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
