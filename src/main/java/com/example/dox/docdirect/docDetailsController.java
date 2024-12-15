package com.example.dox.docdirect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class docDetailsController {
    @FXML
    private Button book_btn;

    @FXML
    private Label date1;

    @FXML
    private Label date2;

    @FXML
    private Label day1;

    @FXML
    private Label day2;

    @FXML
    private RadioButton rdBtn1;

    @FXML
    private RadioButton rdBtn2;

    @FXML
    private ImageView docImage;

    @FXML
    private Label exp;

    @FXML
    private Label fee;

    @FXML
    private Label name;

    @FXML
    private Label proff;

    @FXML
    private Label qualific;

    @FXML
    private ToggleGroup schedule;

    @FXML
    private Label spec;

    @FXML
    private ToggleButton time1_1;

    @FXML
    private ToggleButton time1_2;

    @FXML
    private ToggleButton time2_1;

    @FXML
    private ToggleButton time2_2;

    private ToggleGroup toggleGroup;

    public String selectedSchedule = null;
    public String time = null;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/DocDirectDB";
    private static final String DB_USERNAME = "skt_pie";
    private static final String DB_PASSWORD = "12104053";

    @FXML
    public void initialize() {
        if (schedule.getSelectedToggle() != null) {
            selectedSchedule = (String) schedule.getSelectedToggle().getUserData();
        }
        // Create a ToggleGroup and add all ToggleButtons to it
        toggleGroup = new ToggleGroup();
        time1_1.setToggleGroup(toggleGroup);
        time1_2.setToggleGroup(toggleGroup);
        time2_1.setToggleGroup(toggleGroup);
        time2_2.setToggleGroup(toggleGroup);
    }

    private void navigateToScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleBookNow(ActionEvent event) {
//        System.out.println(time);
//        System.out.println(selectedSchedule);
       // if (selectedSchedule != null||time!=null) {
            storeDataToDB();
            navigateToScene(event, "Myfxmls/checkOut/checkOut.fxml");
//        } else {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("Please select a schedule");
//            alert.showAndWait();
//        }

    }

    private DoctorDataFetcher.Doctor doctor;
    public void setDoctorData(DoctorDataFetcher.Doctor doctor) {
        this.doctor = doctor;

        name.setText(doctor.name);
        proff.setText(doctor.title);
        fee.setText(String.valueOf(doctor.consultationFee));
        docImage.setImage(new Image(doctor.imageUrl, true));
        date1.setText(doctor.schedule.get(0).getDate());
        date2.setText(doctor.schedule.get(1).getDate());
        day1.setText(doctor.schedule.get(0).getDay());
        day2.setText(doctor.schedule.get(1).getDay());
        time1_1.setText(doctor.schedule.get(0).getTime1());
        time1_2.setText(doctor.schedule.get(0).getTime2());
        time2_1.setText(doctor.schedule.get(1).getTime1());
        time2_2.setText(doctor.schedule.get(1).getTime2());
        spec.setText(doctor.specialities.get(0));
        exp.setText(doctor.description);
        qualific.setText(doctor.qualification);
    }
    ArrayList<String> getData() {
        String selectedTime = "";
        String selectedDate = "";

        // Retrieve the currently selected ToggleButton
        ToggleButton selectedButton = (ToggleButton) toggleGroup.getSelectedToggle();
        if (selectedButton != null) {
            selectedTime = selectedButton.getText();
            this.time = selectedTime;

            // Determine the associated date
            if (selectedButton == time1_1 || selectedButton == time1_2) {
                selectedDate = date1.getText();
            } else if (selectedButton == time2_1 || selectedButton == time2_2) {
                selectedDate = date2.getText();
            }
        }
        // Create an ArrayList and populate it
        ArrayList<String> data = new ArrayList<>();
        data.add(this.name.getText()); // Name
        data.add(this.spec.getText()); // Specialization
        data.add(this.fee.getText());  // Fee
        data.add(rdBtn1.isSelected() ? "Online" : "Offline");
        data.add(selectedTime);        // Selected Time
        data.add(selectedDate);        // Selected Date
        return data;
    }
    void storeDataToDB() {
        ArrayList<String> data = getData();
        String table = "user_" + CurrentUser.phoneNumber + "_appointments";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String query = "INSERT INTO " + table + " (appointment_date, appointment_time, doctor_name, speciality, taka, place) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, data.get(5));
            statement.setString(2, data.get(4));
            statement.setString(3, data.get(0));
            statement.setString(4, data.get(1));
            statement.setString(5, data.get(2));
            statement.setString(6, data.get(3));
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Data inserted successfully");
            }
            else {
                System.out.println("Failed to insert data");
            }
            statement.close();
            conn.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
