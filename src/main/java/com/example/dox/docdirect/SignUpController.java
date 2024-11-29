package com.example.dox.docdirect;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signup;

    @FXML
    private ChoiceBox<String> cb_gender;
    private  String[]gndr={"male","female"};

    @FXML
    private CheckBox chkb_certify;

    @FXML
    private DatePicker dp_dateofbirth;

    @FXML
    private PasswordField passfield;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_phonenumber;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        cb_gender.getItems().addAll(gndr);
      btn_signup.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              if(!tf_name.getText().isEmpty() && !tf_email.getText().isEmpty() && !tf_phonenumber.getText().isEmpty() && !passfield.getText().isEmpty() && dp_dateofbirth.getValue() != null && chkb_certify.isSelected() && cb_gender.getValue() != null){
                    DBUtility.signUpUser(event,tf_name.getText(), tf_email.getText(), tf_phonenumber.getText(), passfield.getText(), dp_dateofbirth.getValue(), (String) cb_gender.getValue(),chkb_certify.isSelected());
                }
                else{
                  System.out.println("Please fill all the fields");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please fill all the fields");
                    alert.showAndWait();
              }
              // DBUtility.signUpUser(event,tf_name.getText(), tf_email.getText(), tf_phonenumber.getText(), passfield.getText(), dp_dateofbirth.getValue;

          }
        });
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtility.changeScene(event, "login2.fxml","Log In",null);
            }
        });
    }

}
