package com.example.dox.docdirect;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DoctorCardController {

    @FXML
    private ImageView doctorImage;

    @FXML
    private Label doctorName;

    @FXML
    private Label doctorTitle;

    @FXML
    private Label doctorFee;

    @FXML
    private Button detailsButton;

    private DoctorDataFetcher.Doctor doctor;

    public void setDoctorData(DoctorDataFetcher.Doctor doctor) {
        this.doctor = doctor;

        doctorName.setText(doctor.name);
        doctorTitle.setText(doctor.title);
        doctorFee.setText("Consultation Fee: ৳" + doctor.consultationFee);
        doctorImage.setImage(new Image(doctor.imageUrl, true));

        detailsButton.setOnAction(e -> showDoctorDetails());
    }

    private void showDoctorDetails() {
        // Implement details view logic
        System.out.println("Show details for: " + doctor.name);
    }
}
