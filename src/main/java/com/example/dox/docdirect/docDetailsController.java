package com.example.dox.docdirect;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

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

    private DoctorDataFetcher.Doctor doctor;
    public void setDoctorData(DoctorDataFetcher.Doctor doctor) {
        this.doctor = doctor;

        name.setText(doctor.name);
        proff.setText(doctor.title);
        fee.setText( doctor.consultationFee+"৳");
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
}
