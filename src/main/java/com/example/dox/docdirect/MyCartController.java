package com.example.dox.docdirect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class MyCartController {
    @FXML
    private Button btn_about, btn_contact, btn_doctor, btn_home, btn_service;

    @FXML
    private TableView<AppointmentClass> idtable;

    @FXML
    private TableView<ServiceClass> idtable2;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/DocDirectDB";
    private static final String DB_USERNAME = "skt_pie";
    private static final String DB_PASSWORD = "12104053";

    @FXML
    private void initialize() {
        btn_home.setOnAction(event -> switchScene(event, "Myfxmls/dashboard/dashboard.fxml"));
        btn_service.setOnAction(event -> switchScene(event, "Myfxmls/services/services.fxml"));
        btn_doctor.setOnAction(event -> switchScene(event, "Myfxmls/Doctorss/DoctorListingPage.fxml"));
        btn_about.setOnAction(event -> switchScene(event, "Myfxmls/About/about.fxml"));
        btn_contact.setOnAction(event -> switchScene(event, "Myfxmls/contact/contact.fxml"));

        showTable1();
        showTable2();
        //  updatePriceSummary();
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

    public ObservableList<AppointmentClass> appointmentList() {
        ObservableList<AppointmentClass> appointments = FXCollections.observableArrayList();
        String query = "SELECT * FROM user_" + CurrentUser.phoneNumber + "_appointments";

        try (Connection connectDB = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connectDB.createStatement();
             ResultSet queryResult = statement.executeQuery(query)) {

            while (queryResult.next()) {
                AppointmentClass appointment = new AppointmentClass(
                        queryResult.getInt("id"), // Assuming there's an 'id' column in your table
                        queryResult.getString("appointment_date"),
                        queryResult.getString("appointment_time"),
                        queryResult.getString("doctor_name"),
                        queryResult.getString("speciality"),
                        queryResult.getDouble("taka"),
                        queryResult.getString("place"),
                        queryResult.getString("payment_status")
                );
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Sort the appointments by id in descending order
        FXCollections.sort(appointments, (a1, a2) -> Integer.compare(a2.getId(), a1.getId()));

        return appointments;
    }

    public ObservableList<ServiceClass> serviceList() {
        ObservableList<ServiceClass> services = FXCollections.observableArrayList();
        String query = "SELECT * FROM user_" + CurrentUser.phoneNumber + "_services";

        try (Connection connectDB = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connectDB.createStatement();
             ResultSet queryResult = statement.executeQuery(query)) {

            while (queryResult.next()) {
                ServiceClass service = new ServiceClass(
                        queryResult.getInt("id"), // Assuming there's an 'id' column in your table
                        queryResult.getString("service_name"),
                        queryResult.getInt("quantity"),
                        queryResult.getDouble("price"),
                        queryResult.getString("payment_status")
                );
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Sort the services by id in descending order
        FXCollections.sort(services, (s1, s2) -> Integer.compare(s2.getId(), s1.getId()));

        return services;
    }


    private ObservableList<AppointmentClass> appointments;

    void showTable1() {
        appointments = appointmentList();
        idtable.getColumns().clear();

        TableColumn<AppointmentClass, String> dateColumn = new TableColumn<>("Appointment Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));

        TableColumn<AppointmentClass, String> timeColumn = new TableColumn<>("Appointment Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));

        TableColumn<AppointmentClass, String> doctorColumn = new TableColumn<>("Doctor Name");
        doctorColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName"));

        TableColumn<AppointmentClass, String> specialityColumn = new TableColumn<>("Speciality");
        specialityColumn.setCellValueFactory(new PropertyValueFactory<>("speciality"));

        TableColumn<AppointmentClass, Double> costColumn = new TableColumn<>("Cost");
        costColumn.setCellValueFactory(new PropertyValueFactory<>("taka"));

        TableColumn<AppointmentClass, String> placeColumn = new TableColumn<>("Place");
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));

        TableColumn<AppointmentClass, String> paymentStatusColumn = new TableColumn<>("Payment Status");
        paymentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("payment_status"));

        idtable.getColumns().addAll(dateColumn, timeColumn, doctorColumn, specialityColumn, costColumn, placeColumn,paymentStatusColumn );
        idtable.setItems(appointments);
    }

    private ObservableList<ServiceClass> services;

    void showTable2() {
        services = serviceList();
        idtable2.getColumns().clear();

        TableColumn<ServiceClass, String> nameColumn = new TableColumn<>("Service Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));

        TableColumn<ServiceClass, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<ServiceClass, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<ServiceClass, String> paymentStatusColumn = new TableColumn<>("Payment Status");
        paymentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("payment_status"));

        idtable2.getColumns().addAll(nameColumn, quantityColumn, priceColumn,paymentStatusColumn);
        idtable2.setItems(services);
    }
}

