package com.example.dox.docdirect;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactController {

    @FXML
    private Button aboutButton;

    @FXML
    private Button bookAppointmentButton;

    @FXML
    private Button contactButton;

    @FXML
    private TextField emailField;

    @FXML
    private Button homeButton;

    @FXML
    private TextField messageField;

    @FXML
    private TextField nameField;

    @FXML
    private Button ourDoctorsButton;

    @FXML
    private TextField phoneField;

    @FXML
    private Button servicesButton;

    @FXML
    private Button submitButton;

    // Utility method to change scenes
    private void changeScene(String fxmlFileName) {
        try {
            Stage stage = (Stage) homeButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Unable to load scene: " + fxmlFileName, Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void initialize() {
        homeButton.setOnAction(event -> changeScene("Myfxmls/dashboard/dashboard.fxml"));
        ourDoctorsButton.setOnAction(event -> changeScene("Myfxmls/Doctorss/DoctorListingPage.fxml"));
        servicesButton.setOnAction(event -> changeScene("Myfxmls/services/services.fxml"));
        aboutButton.setOnAction(event -> changeScene("Myfxmls/About/about.fxml"));
        contactButton.setOnAction(event -> changeScene("Myfxmls/contact/contact.fxml"));
        bookAppointmentButton.setOnAction(event -> changeScene("Myfxmls/Doctorss/DoctorListingPage.fxml"));
        submitButton.setOnAction(event -> handleSubmit());
    }

    private void handleSubmit() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String message = messageField.getText().trim();

        // Validation
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || message.isEmpty()) {
            showAlert("Validation Error", "All fields must be filled out.", Alert.AlertType.WARNING);
            return;
        }

        if (!phone.matches("\\d{11}")) {
            showAlert("Validation Error", "Phone number must be exactly 11 digits.", Alert.AlertType.WARNING);
            return;
        }

        // Database insertion
        String url = "jdbc:mysql://localhost:3306/DocDirectDB";
        String user = "skt_pie";
        String password = "12104053";
        String query = "INSERT INTO `FeedbackTable` (Name, Email, `Phone Number`, Comment) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, message);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                showAlert("Success", "Your feedback has been submitted successfully!", Alert.AlertType.INFORMATION);

                // Clear the form fields
                nameField.clear();
                emailField.clear();
                phoneField.clear();
                messageField.clear();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Could not save your feedback. Please try again later.", Alert.AlertType.ERROR);
        }
    }

    // Utility method to display alerts
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
