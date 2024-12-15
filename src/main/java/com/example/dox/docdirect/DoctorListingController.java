package com.example.dox.docdirect;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DoctorListingController {

    @FXML
    private GridPane doctorGrid;
        private Stage stage;
        private Scene scene;
        private Parent root;
        private int numColumns = 4;


    @FXML
    private Button btn_mycart;

        @FXML
        private Button about_btn;
        @FXML
        private Button contact_btn;
        @FXML
        private Button doctor_btn;

        @FXML
        private Button home_btn;

        @FXML
        private ScrollPane scrollPane;

        @FXML
        private Button services_btn;
    public void initialize() {
        home_btn.setOnAction(event -> switchScene(event, "Myfxmls/dashboard/dashboard.fxml"));
        services_btn.setOnAction(event -> switchScene(event, "Myfxmls/services/services.fxml"));
        doctor_btn.setOnAction(event -> switchScene(event, "Myfxmls/Doctorss/DoctorListingPage.fxml"));
        about_btn.setOnAction(event -> switchScene(event, "Myfxmls/About/about.fxml"));
        contact_btn.setOnAction(event -> switchScene(event, "Myfxmls/contact/contact.fxml"));
        btn_mycart.setOnAction(event -> switchScene(event, "Myfxmls/myCart/viewCart.fxml"));

        String apiUrl = "https://api.myjson.online/v1/records/b9ee7919-1a34-4016-9d6d-a9d8484cd99e";
        List<DoctorDataFetcher.Doctor> doctors = DoctorDataFetcher.fetchDoctorsFromApi(apiUrl);

        if (doctors != null) {
            int column = 0;
            int row = 0;

            for (int i = 0; i < doctors.size(); i++) {
                DoctorDataFetcher.Doctor doctor = doctors.get(i);

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Myfxmls/Doctorss/DoctorCard.fxml"));
                    Pane doctorCard = loader.load();

                    DoctorCardController controller = loader.getController();
                    controller.setDoctorData(doctor);

                    doctorGrid.add(doctorCard, column, row);

                    column++;
                    if (column == numColumns) { // Adjust for 3 doctors per row
                        column = 0;
                        row++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
}
