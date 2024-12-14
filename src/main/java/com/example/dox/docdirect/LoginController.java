package com.example.dox.docdirect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private TextField tf_phonenumber;

    @FXML
    private TextField tf_password;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signup;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/DocDirectDB";
    private static final String DB_USERNAME = "skt_pie";
    private static final String DB_PASSWORD = "12104053";

    @FXML
    void handleLogin(ActionEvent event) {
        String phoneNumber = tf_phonenumber.getText().trim();
        String password = tf_password.getText().trim();

        if (phoneNumber.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Phone number and password are required.");
            return;
        }

        if (validateLogin(phoneNumber, password)) {
            switchToDashboard();
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid phone number or password.");
        }
    }

    private boolean validateLogin(String phoneNumber, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM users WHERE phonenumber = ? AND password = ?")) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean isValid = resultSet.next();
            if (isValid) {
                String name=resultSet.getString("name");
                String email=resultSet.getString("email");
                String dateofbirth=resultSet.getString("dateofbirth");
                String gender=resultSet.getString("gender");
                CurrentUser.setUserData(phoneNumber,name,email,dateofbirth,gender);
            }

            return isValid;
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while connecting to the database.");
        }
        return false;
    }

    private void switchToDashboard() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Myfxmls/dashboard/dashboard.fxml"));
            Stage stage = (Stage) btn_login.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not load the dashboard.");
        }
    }
    @FXML
    void switchToSignUp(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Myfxmls/signup/Signup.fxml"));
            AnchorPane root = fxmlLoader.load();
            Stage stage = (Stage) btn_login.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not load the dashboard.");
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
