package com.example.dox.docdirect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Appointment Model Class
class Appointment {
    private String appointmentDate;
    private String appointmentTime;
    private String doctorName;
    private String speciality;
    private double taka;
    private String place;

    public Appointment(String appointmentDate, String appointmentTime, String doctorName, String speciality, double taka, String place) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.doctorName = doctorName;
        this.speciality = speciality;
        this.taka = taka;
        this.place = place;
    }

    public String getAppointmentDate() { return appointmentDate; }
    public String getAppointmentTime() { return appointmentTime; }
    public String getDoctorName() { return doctorName; }
    public String getSpeciality() { return speciality; }
    public double getTaka() { return taka; }
    public String getPlace() { return place; }
}

// Service Model Class
class Service {
    private String serviceName;
    private int quantity;
    private double price;

    public Service(String serviceName, int quantity, double price) {
        this.serviceName = serviceName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getServiceName() { return serviceName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}

public class CartController {

    @FXML
    private TableView<Appointment> appointmentTableView;
    @FXML
    private TableColumn<Appointment, String> colAppointmentDate;
    @FXML
    private TableColumn<Appointment, String> colAppointmentTime;
    @FXML
    private TableColumn<Appointment, String> colDoctorName;
    @FXML
    private TableColumn<Appointment, String> colSpeciality;
    @FXML
    private TableColumn<Appointment, Double> colTaka;
    @FXML
    private TableColumn<Appointment, String> colPlace;

    @FXML
    private TableView<Service> serviceTableView;
    @FXML
    private TableColumn<Service, String> colServiceName;
    @FXML
    private TableColumn<Service, Integer> colQuantity;
    @FXML
    private TableColumn<Service, Double> colPrice;
    @FXML
    private Button itmRemBtn;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/DocDirectDB";
    private static final String DB_USERNAME = "skt_pie";
    private static final String DB_PASSWORD = "12104053";

    @FXML
    public void initialize() {
        // Initialize Appointment TableView
        colAppointmentDate.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        colAppointmentTime.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));
        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colSpeciality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        colTaka.setCellValueFactory(new PropertyValueFactory<>("taka"));
        colPlace.setCellValueFactory(new PropertyValueFactory<>("place"));

        // Initialize Service TableView
        colServiceName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Load Data into Tables
        loadAppointments("9876543210"); // Replace with the actual user phone number
        loadServices("9876543210"); // Replace with the actual user phone number
    }

    private void loadAppointments(String phoneNumber) {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        String sanitizedPhoneNumber = phoneNumber.replaceAll("[^a-zA-Z0-9]", "");
        String query = "SELECT * FROM user_" + sanitizedPhoneNumber + "_appointments";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                appointments.add(new Appointment(
                        resultSet.getString("appointment_date"),
                        resultSet.getString("appointment_time"),
                        resultSet.getString("doctor_name"),
                        resultSet.getString("speciality"),
                        resultSet.getDouble("taka"),
                        resultSet.getString("place")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        appointmentTableView.setItems(appointments);
    }

    private void loadServices(String phoneNumber) {
        ObservableList<Service> services = FXCollections.observableArrayList();
        String sanitizedPhoneNumber = phoneNumber.replaceAll("[^a-zA-Z0-9]", "");
        String query = "SELECT * FROM user_" + sanitizedPhoneNumber + "_services";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                services.add(new Service(
                        resultSet.getString("service_name"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("price")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        serviceTableView.setItems(services);
    }

    public void removeItemFromAppointment() {
        removeSelectedRow(appointmentTableView);
    }
    public void removeItemFromService() {
        removeSelectedRow(serviceTableView);
    }

    public void removeSelectedRow(TableView<?> tableView) {
        // Get the selected item(s)
        Object selectedItem = tableView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // Confirm the deletion with an alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure you want to delete this item?");
            alert.setContentText("Once deleted, this cannot be undone.");

            // Wait for user's response
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // If the user confirmed, remove the selected item from the table
                    tableView.getItems().remove(selectedItem);

                    // Optionally, remove the item from the database if needed
                    // deleteItemFromDatabase(selectedItem);
                }
            });
        } else {
            // Show an alert if no item is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No item selected");
            alert.setContentText("Please select an item to delete.");
            alert.showAndWait();
        }
    }
}
