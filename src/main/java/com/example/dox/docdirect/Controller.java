package com.example.dox.docdirect;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    // ...
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_signup;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_phonenumber;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Login button clicked");
                DBUtility.logInUser(event, tf_phonenumber.getText(), tf_password.getText());
                // ...
            }
        });

        btn_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtility.changeScene(event, "Signup.fxml", "Sign Up", null);
                // ...
            }
        });
    }


    public void setUserInformation(String username) {

    }
}