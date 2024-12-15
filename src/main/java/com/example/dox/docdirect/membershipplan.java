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

public class membershipplan {
    @FXML
    private Button contact;

    @FXML
    private Button doctor;

    @FXML
    private Button mycart_btn;

    @FXML
    private Button home;

    @FXML
    private Button service;
    @FXML
    private Button about;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private void initialize() {
        home.setOnAction(event -> switchScene(event, "Myfxmls/dashboard/dashboard.fxml"));
        service.setOnAction(event -> switchScene(event, "Myfxmls/services/services.fxml"));
        doctor.setOnAction(event -> switchScene(event, "Myfxmls/Doctorss/DoctorListingPage.fxml"));
        about.setOnAction(event -> switchScene(event, "Myfxmls/About/about.fxml"));
        contact.setOnAction(event -> switchScene(event, "Myfxmls/contact/contact.fxml"));
        mycart_btn.setOnAction(event -> switchScene(event, "Myfxmls/myCart/viewCart.fxml"));
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

    private static final String DB_URL = "jdbc:mysql://localhost:3306/DocDirectDB";
    private static final String DB_USERNAME = "skt_pie";
    private static final String DB_PASSWORD = "12104053";
    public void addPlanToCart(String planName, double price) {
        String phn = CurrentUser.phoneNumber;
        String table = "user_" + phn + "_services";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String findQuery = "SELECT * FROM " + table + " WHERE service_name = ?";
            PreparedStatement findStatement = conn.prepareStatement(findQuery);
            findStatement.setString(1, planName);
            ResultSet rs = findStatement.executeQuery();
            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                String updateQuery = "UPDATE " + table + " SET quantity = ? WHERE service_name = ?";
                PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                updateStatement.setInt(1, quantity + 1);
                updateStatement.setString(2, planName);
                updateStatement.executeUpdate();
            } else {
                String setQuery = "INSERT INTO " + table + " (service_name, quantity, price) VALUES (?, ?, ?)";
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
        addPlanToCart("Gold Annual Membership Plan", 12000.0);
    }

    public void addPlan2ToCart() {
        addPlanToCart("Silver Annual Membership Plan", 8400);
    }

    public void addPlan3ToCart() {
        addPlanToCart("Platinum Annual Membership Plan", 21000.0);
    }

    public void addPlan4ToCart() {
        addPlanToCart("Pre-Diabetes Plan", 27000.0);
    }

    public void addPlan5ToCart() {
        addPlanToCart("Diabetes Plan", 42000.0);
    }

    public void addPlan6ToCart() {
        addPlanToCart("Cardiac and Hypertension Plan", 42000.0);
    }

    public void addPlan7ToCart() {
        addPlanToCart("12 Months Video Consultation Plan", 6250.0);
    }

    public void addPlan8ToCart() {
        addPlanToCart("6 Months Video Consultation Plan", 5050.0);
    }

    public void addPlan9ToCart() {
        addPlanToCart("3 Months Video Consultation Plan",3850.0 );
    }
}