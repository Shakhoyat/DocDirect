package com.example.dox.docdirect;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class membershipPlan3Controller {

    @FXML
    private Button btn_about;

    @FXML
    private Button btn_bookAppointment;

    @FXML
    private Button btn_contact;

    @FXML
    private Button btn_doctor;

    @FXML
    private Button btn_home;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/DocDirectDB";
    private static final String DB_USERNAME = "skt_pie";
    private static final String DB_PASSWORD = "12104053";

    @FXML
    private Button btn_service;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private void initialize() {
        btn_home.setOnAction(event -> switchScene(event, "Myfxmls/dashboard/dashboard.fxml"));
        btn_service.setOnAction(event -> switchScene(event, "Myfxmls/services/services.fxml"));
        btn_doctor.setOnAction(event -> switchScene(event, "Myfxmls/Doctorss/DoctorListingPage.fxml"));
        btn_about.setOnAction(event -> switchScene(event, "Myfxmls/About/about.fxml"));
        btn_contact.setOnAction(event -> switchScene(event, "Myfxmls/contact/contact.fxml"));
        btn_bookAppointment.setOnAction(event -> switchScene(event, "Myfxmls/Doctorss/DoctorListingPage.fxml"));
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
    public void addPlanToCart(String planName, double price) {
        String phn = CurrentUser.phoneNumber;
        String table = "user_" + phn + "_services";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String findQuery = "SELECT * FROM "+ table +" WHERE service_name = ?";
            PreparedStatement findStatement = conn.prepareStatement(findQuery);
            findStatement.setString(1, planName);
            ResultSet rs = findStatement.executeQuery();
            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                String updateQuery = "UPDATE "+ table +" SET quantity = ? WHERE service_name = ?";
                PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                updateStatement.setInt(1, quantity + 1);
                updateStatement.setString(2, planName);
                updateStatement.executeUpdate();
            } else {
                String setQuery = "INSERT INTO "+ table +" (service_name, quantity, price) VALUES (?, ?, ?)";
                PreparedStatement setStatement = conn.prepareStatement(setQuery);
                setStatement.setString(1, planName);
                setStatement.setInt(2, 1);
                setStatement.setDouble(3, price);
                setStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPlan1ToCart() {
        addPlanToCart("3 Months Video Consultation Plan", 3850.0);
    }
    public void addPlan2ToCart() {
        addPlanToCart("6 Months Video Consultation Plan", 5050.0);
    }
    public void addPlan3ToCart() {
        addPlanToCart("12 Months Video Consultation Plan", 6250.0);
    }
}

