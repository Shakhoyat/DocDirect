package com.example.dox.docdirect;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

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

        detailsButton.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Myfxmls/Doctorss/docDetails.fxml"));
                Node doctorDetails = loader.load();

                docDetailsController controller = loader.getController();
                controller.setDoctorData(doctor);

                Node parent = detailsButton.getParent();
                while (parent != null && !(parent instanceof ScrollPane)) {
                    parent = parent.getParent();
                }

                // If a ScrollPane is found, set the content
                if (parent instanceof ScrollPane pane) {
                    pane.setContent(doctorDetails);
                } else {
                    System.err.println("ScrollPane not found in the parent hierarchy.");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
    }
}