package com.example.dox.docdirect;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LogOutController implements Initializable {

    @FXML
    private Button btn_logout;

    @FXML
    private Label lbl_email;

    @FXML
    private Label lbl_welcome;

    public void initialize(URL location, ResourceBundle rb) {
        btn_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtility.changeScene(event, "login2.fxml","Log In",null);

            }
        });

    }

}
