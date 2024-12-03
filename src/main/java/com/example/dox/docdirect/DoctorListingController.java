package com.example.dox.docdirect;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.List;

public class DoctorListingController {

    @FXML
    private GridPane doctorGrid;

    private int numColumns = 2;

    public void initialize() {
        String apiUrl = "https://nayeemcode.pythonanywhere.com/data?file=sujon&key=jhbfhsdjdjsadbjsd";
        List<DoctorDataFetcher.Doctor> doctors = DoctorDataFetcher.fetchDoctorsFromApi(apiUrl);

        if (doctors != null) {
            int column = 0;
            int row = 0;

            for (int i = 0; i < doctors.size(); i++) {
                DoctorDataFetcher.Doctor doctor = doctors.get(i);

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorCard.fxml"));
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
}
