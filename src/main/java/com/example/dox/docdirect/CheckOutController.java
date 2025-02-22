package com.example.dox.docdirect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.*;

public class CheckOutController {

    @FXML
    private Button btn_about, btn_contact, btn_doctor, btn_home, btn_service;

    @FXML
    private TableView<AppointmentClass> idtable;

    @FXML
    private TableView<ServiceClass> idtable2;

    @FXML
    private Label id_name, id_phone,id_email,id_dateOfBirth, lbl_ServiceCost, lbl_appointmentCost, lbl_subTotalCost;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/DocDirectDB";
    private static final String DB_USERNAME = "skt_pie";
    private static final String DB_PASSWORD = "12104053";

    private double subtotal=0;


    private void updatePriceSummary() {
        // Calculate total Appointment bill
        double appointmentTotal = appointments.stream()
                .mapToDouble(AppointmentClass::getTaka) // Assuming `getTaka` returns the cost
                .sum();

        // Calculate total Service bill
        double serviceTotal = services.stream()
                .mapToDouble(service -> service.getQuantity() * service.getPrice())
                .sum();

        // Calculate subtotal
         subtotal = appointmentTotal + serviceTotal;

        // Update labels
        lbl_appointmentCost.setText(String.format("৳ %.2f", appointmentTotal));
        lbl_ServiceCost.setText(String.format("৳ %.2f", serviceTotal));
        lbl_subTotalCost.setText(String.format("৳ %.2f", subtotal));
    }

    @FXML
    private void initialize() {
        id_email.setText(CurrentUser.email);
        id_name.setText(CurrentUser.userName);
        id_phone.setText(CurrentUser.phoneNumber);
        id_dateOfBirth.setText(CurrentUser.DateOfBirth);
        btn_home.setOnAction(event -> switchScene(event, "Myfxmls/dashboard/dashboard.fxml"));
        btn_service.setOnAction(event -> switchScene(event, "Myfxmls/services/services.fxml"));
        btn_doctor.setOnAction(event -> switchScene(event, "Myfxmls/Doctorss/DoctorListingPage.fxml"));
        btn_about.setOnAction(event -> switchScene(event, "Myfxmls/About/about.fxml"));
        btn_contact.setOnAction(event -> switchScene(event, "Myfxmls/contact/contact.fxml"));

        showTable1();
        showTable2();
        updatePriceSummary();
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
        String query = "SELECT * FROM user_" + CurrentUser.phoneNumber + "_appointments WHERE payment_status = 'UNPAID'";

        try (Connection connectDB = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connectDB.createStatement();
             ResultSet queryResult = statement.executeQuery(query)) {

            while (queryResult.next()) {
                AppointmentClass appointment = new AppointmentClass(
                        queryResult.getInt("id"),
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
        return appointments;
    }
    public ObservableList<ServiceClass> serviceList() {
        ObservableList<ServiceClass> services = FXCollections.observableArrayList();
        String query = "SELECT * FROM user_" + CurrentUser.phoneNumber + "_services WHERE payment_status = 'UNPAID'";

        try (Connection connectDB = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connectDB.createStatement();
             ResultSet queryResult = statement.executeQuery(query)) {

            while (queryResult.next()) {
                ServiceClass service = new ServiceClass(
                        queryResult.getInt("id"),
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

        // Add Delete button column
        TableColumn<AppointmentClass, Void> deleteColumn = createDeleteButtonColumn("Delete", idtable);

        idtable.getColumns().addAll(dateColumn, timeColumn, doctorColumn, specialityColumn, costColumn, placeColumn, deleteColumn);
        idtable.setItems(appointments);
    }

    private ObservableList<ServiceClass> services;

    void showTable2() {
        services = serviceList();
        idtable2.getColumns().clear();

        TableColumn<ServiceClass, String> nameColumn = new TableColumn<>("Service Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));

        TableColumn<ServiceClass, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellFactory(param -> new TableCell<>() {
            private final Button btnDecrease = new Button("-");
            private final Button btnIncrease = new Button("+");
            private final Label lblQuantity = new Label();

            {
                btnDecrease.setOnAction(event -> updateQuantity(-1));
                btnIncrease.setOnAction(event -> updateQuantity(1));

                // Style buttons if needed
                btnDecrease.getStyleClass().add("quantity-button");
                btnIncrease.getStyleClass().add("quantity-button");
            }

            private void updateQuantity(int delta) {
                ServiceClass service = getTableView().getItems().get(getIndex());
                int newQuantity = Math.max(service.getQuantity() + delta, 1); // Minimum quantity is 1
                service.setQuantity(newQuantity);
                lblQuantity.setText(String.valueOf(newQuantity));
                idtable2.refresh(); // Refresh table to reflect changes

                // Update price summary after quantity change
                updatePriceSummary();
            }

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    ServiceClass service = getTableView().getItems().get(getIndex());
                    lblQuantity.setText(String.valueOf(service.getQuantity()));

                    // Layout for buttons and label
                    HBox hbox = new HBox(5, btnDecrease, lblQuantity, btnIncrease);
                    hbox.setStyle("-fx-alignment: center;");
                    setGraphic(hbox);
                }
            }
        });

        TableColumn<ServiceClass, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Add Delete button column
        TableColumn<ServiceClass, Void> deleteColumn = createDeleteButtonColumn("Delete", idtable2);

        idtable2.getColumns().addAll(nameColumn, quantityColumn, priceColumn, deleteColumn);
        idtable2.setItems(services);
    }
    private <T> TableColumn<T, Void> createDeleteButtonColumn(String columnName, TableView<T> tableView) {
        TableColumn<T, Void> colBtn = new TableColumn<>(columnName);

        Callback<TableColumn<T, Void>, TableCell<T, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btn = new Button(columnName);

            {
                btn.getStyleClass().add("red-cross-button");
                btn.setText("×");
                btn.setOnAction(event -> {
                    T selectedItem = getTableView().getItems().get(getIndex());
                    deleteFromDatabase(selectedItem);
                    tableView.getItems().remove(selectedItem);
                    updatePriceSummary();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        };

        colBtn.setCellFactory(cellFactory);
        return colBtn;
    }

    private <T> void deleteFromDatabase(T selectedItem) {
        String query = null;

        if (selectedItem instanceof AppointmentClass appointment) {
            query = "DELETE FROM user_" + CurrentUser.phoneNumber + "_appointments WHERE appointment_date = ? AND appointment_time = ?";
            try (Connection connectDB = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                 PreparedStatement ps = connectDB.prepareStatement(query)) {

                ps.setString(1, appointment.getAppointmentDate());
                ps.setString(2, appointment.getAppointmentTime());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (selectedItem instanceof ServiceClass service) {
            query = "DELETE FROM user_" + CurrentUser.phoneNumber + "_services WHERE service_name = ?";
            try (Connection connectDB = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                 PreparedStatement ps = connectDB.prepareStatement(query)) {

                ps.setString(1, service.getServiceName());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleProceedToCheckout(ActionEvent event) {
        try {
            // Load the SSLpage.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Myfxmls/SSLCommerz/SSLpage.fxml"));
            Parent root = loader.load();



            // Get the current stage from the event source
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Set the new scene with the loaded FXML
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Optionally, set the stage title
            stage.setTitle("SSL Payment Page");

            // Show the updated stage
            stage.show();
            SSLcontoller ssLcontoller = loader.getController();
            ssLcontoller.handleProceedToCheckout(subtotal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
