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

public class healthcheckuppackageController {
        @FXML
        private Button buy1;

        @FXML
        private Button buy10;

        @FXML
        private Button buy11;

        @FXML
        private Button buy2;

        @FXML
        private Button buy3;

        @FXML
        private Button buy4;

        @FXML
        private Button buy5;

        @FXML
        private Button buy6;

        @FXML
        private Button buy7;

        @FXML
        private Button buy8;

        @FXML
        private Button buy9;

        @FXML
        private Button cart1;

        @FXML
        private Button cart10;

        @FXML
        private Button cart11;

        @FXML
        private Button cart2;

        @FXML
        private Button cart3;

        @FXML
        private Button cart4;

        @FXML
        private Button cart5;

        @FXML
        private Button cart6;

        @FXML
        private Button cart7;

        @FXML
        private Button cart8;

        @FXML
        private Button cart9;

        @FXML
        private Button about;

        @FXML
        private Button contact;

        @FXML
        private Button doctors;

        @FXML
        private Button home;

        @FXML
        private Button service;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void initialize() {
        home.setOnAction(event -> switchScene(event, "dashboard.fxml"));
        service.setOnAction(event -> switchScene(event, "services.fxml"));
        doctors.setOnAction(event -> switchScene(event, "DoctorListingPage.fxml"));
        about.setOnAction(event -> switchScene(event, "about.fxml"));
        contact.setOnAction(event -> switchScene(event, "contact.fxml"));

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
        addPlanToCart("Her Health Check(Below 40 years)", 13800.0);
    }

    public void addPlan2ToCart() {
        addPlanToCart("Her Health Check( 40 to 65  years)", 22200.0);
    }

    public void addPlan3ToCart() {
        addPlanToCart("Her Health Check(Above 65 years)", 26400.0);
    }

    public void addPlan4ToCart() {
        addPlanToCart("His Health Check(Below 40 years)", 13800.0);
    }

    public void addPlan5ToCart() {
        addPlanToCart("His Health Check( 40 to 65  years)", 22200.0);
    }

    public void addPlan6ToCart() {
        addPlanToCart("His Health Check(Above 65 years)", 26400.0);
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
    public void addPlan10ToCart() {
        addPlanToCart("Full Body Health Check",9900.0 );
    }
    public void addPlan11ToCart() {
        addPlanToCart("Home Health Check",8900.0 );
    }
    public void addPlan12ToCart() {
        addPlanToCart("Comprehensive Cardiac and Hypertension Health Check",24900.0 );
    }
    public void addPlan13ToCart() {
        addPlanToCart("Comprehensive Diabetics Health Check",25800.0 );
    }
    public void addPlan14ToCart() {
        addPlanToCart("Child Health Check(2 to 16 Years)",6200.0 );
    }

    }


