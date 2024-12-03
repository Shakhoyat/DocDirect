package com.example.dox.docdirect;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ConsultationController {

    @FXML
    private Button book_appointment_btn1;

    @FXML
    private Button book_appointment_btn2;

    @FXML
    private Button book_appointment_btn3;

    @FXML
    private Button btn_about;

    @FXML
    private Button contact_btn;

    @FXML
    private Button home_btn;

    @FXML
    private Button our_doctor_btn;

    @FXML
    private Button services_btn;

 //   @FXML
   // private Button amar_jotno_btn; // Added Amar Jotno button

    // Reference to the primary stage for scene changes
    private Stage stage;

    // Method to set the stage from the main application
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        // Set action handlers for each button
        home_btn.setOnAction(e -> loadScene("dashboard.fxml"));
        services_btn.setOnAction(e -> loadScene("services.fxml"));
        our_doctor_btn.setOnAction(e -> loadScene("ourDoctors.fxml"));
        btn_about.setOnAction(e -> loadScene("about.fxml"));
        contact_btn.setOnAction(e -> loadScene("contact.fxml"));
        book_appointment_btn1.setOnAction(e -> loadScene("ourDoctors.fxml"));
        book_appointment_btn2.setOnAction(e -> loadScene("ourDoctors.fxml"));
        book_appointment_btn3.setOnAction(e -> loadScene("ourDoctors.fxml"));
     //   amar_jotno_btn.setOnAction(e -> loadScene("membershipPlan3.fxml")); // For Amar Jotno Plans
    }

    // Method to load a new scene
    private void loadScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Assuming stage is set externally or accessible
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception with proper logging in a real app
        }
    }
}
