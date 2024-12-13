package com.example.dox.docdirect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SignUpController {

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_phonenumber;

    @FXML
    private ChoiceBox<String> cb_gender;

    @FXML
    private DatePicker dp_dateofbirth;

    @FXML
    private PasswordField passfield;

    @FXML
    private CheckBox chkb_certify;

    @FXML
    private Button btn_signup;

    @FXML
    private Button btn_login;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/DocDirectDB";
    private static final String DB_USERNAME = "skt_pie";
    private static final String DB_PASSWORD = "12104053";

    @FXML
    public void initialize() {
        // Initialize gender choice box with the correct options based on the enum type
        cb_gender.getItems().addAll("Male", "Female");
    }

    @FXML
    void handleSignUp(ActionEvent event) {
        String name = tf_name.getText().trim();
        String email = tf_email.getText().trim();
        String phoneNumber = tf_phonenumber.getText().trim();
        String gender = cb_gender.getValue();
        String dateOfBirth = (dp_dateofbirth.getValue() != null) ? dp_dateofbirth.getValue().toString() : null;
        String password = passfield.getText().trim();

        // Validate inputs before saving
        if (!validateInput(name, email, phoneNumber, gender, dateOfBirth, password)) {
            return;
        }

        // Ensure the user has checked the certification box
        if (!chkb_certify.isSelected()) {
            showAlert(Alert.AlertType.ERROR, "Certification Error", "You must certify that the information provided is true.");
            return;
        }

        // Save the user to the database
        if (saveUserToDatabase(phoneNumber, name, email, password, dateOfBirth, gender)) {
            showAlert(Alert.AlertType.INFORMATION, "SignUp Successful", "Your account has been created successfully!");
            clearFields();
        }
    }

    private boolean validateInput(String name, String email, String phoneNumber, String gender, String dateOfBirth, String password) {
        if (name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || gender == null || dateOfBirth == null || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields must be filled.");
            return false;
        }

        if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Invalid email format.");
            return false;
        }

        if (!phoneNumber.matches("\\d{10}")) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Phone number must be 10 digits.");
            return false;
        }

        if (password.length() < 6) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Password must be at least 6 characters long.");
            return false;
        }

        return true;
    }

    private boolean saveUserToDatabase(String phoneNumber, String name, String email, String password, String dateOfBirth, String gender) {
        int userId = -1;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO users (phonenumber, name, email, password, dateofbirth, gender, value) VALUES (?, ?, ?, ?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, dateOfBirth);
            preparedStatement.setString(6, gender);
            preparedStatement.setInt(7, 0); // Assuming value is set to 0 for a new user.

            preparedStatement.executeUpdate();

            // Retrieve the generated user ID
            var rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                userId = rs.getInt(1);
            }

            // Create user-specific tables
            if (phoneNumber != null ){
                createUserSpecificTables(phoneNumber);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Could not save user information to the database.");
        }
        return false;
    }

    private void createUserSpecificTables(String phoneNumber) {
        // Sanitize phoneNumber to use in table names
        String sanitizedPhoneNumber = phoneNumber.replaceAll("[^a-zA-Z0-9]", "");
        System.out.println("1");
        // Updated Services Table
        String servicesTableQuery = String.format(
                "CREATE TABLE IF NOT EXISTS user_%s_services (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "service_name VARCHAR(100), " +
                        "quantity INT, " +
                        "price DECIMAL(10, 2))", sanitizedPhoneNumber);
        // Updated Appointments Table
        String appointmentsTableQuery = String.format(
                "CREATE TABLE IF NOT EXISTS user_%s_appointments (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "appointment_date VARCHAR(15), " +
                        "appointment_time VARCHAR(15), " +
                        "doctor_name VARCHAR(100), " +
                        "speciality VARCHAR(100), " +
                        "taka DECIMAL(10, 2), " +
                        "place VARCHAR(150))", sanitizedPhoneNumber);
        System.out.println("2");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            System.out.println("3");
            // Execute the queries
            connection.createStatement().execute(servicesTableQuery);
            connection.createStatement().execute(appointmentsTableQuery);
            System.out.println("4");
            System.out.println("User-specific tables created successfully for phone number: " + phoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Table Creation Error", "Could not create user-specific tables.");
        }
    }



    private void clearFields() {
        tf_name.clear();
        tf_email.clear();
        tf_phonenumber.clear();
        cb_gender.setValue(null);
        dp_dateofbirth.setValue(null);
        passfield.clear();
        chkb_certify.setSelected(false);
    }

    @FXML
    void handleLoginNavigation(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/dox/docdirect/login2.fxml"));
            AnchorPane root = fxmlLoader.load();
            Stage stage = (Stage) btn_login.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not navigate to the login page.");
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
