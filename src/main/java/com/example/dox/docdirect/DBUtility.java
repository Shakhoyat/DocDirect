package com.example.dox.docdirect;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class DBUtility {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String phonenumber) {
        Parent root = null;
        if (phonenumber != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtility.class.getResource(fxmlFile));
                root = loader.load();
//                Controller controller = loader.getController();
//                controller.setUserInformation(phonenumber);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtility.class.getResource(fxmlFile));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String name, String email, String phonenumber, String password, LocalDate dateofbirth, String gender, Boolean value) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultset = null;
        try {
            // using database DocDirectDB
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DocDirectDB", "skt_pie", "12104053");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE phonenumber=?");
            psCheckUserExists.setString(1, phonenumber);
            resultset = psCheckUserExists.executeQuery();
            if (resultset.next()) {
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User already exists");
                alert.show();

            } else {
                psInsert = connection.prepareStatement("INSERT INTO users(name,email,phonenumber,password,dateofbirth,gender,value) VALUES(?,?,?,?,?,?,?)");
                psInsert.setString(1, name);
                psInsert.setString(2, email);
                psInsert.setString(3, phonenumber);
                psInsert.setString(4, password);
                psInsert.setDate(5, Date.valueOf(dateofbirth));
                psInsert.setString(6,gender);
                psInsert.setBoolean(7, value);
                psInsert.executeUpdate();
                System.out.println("before");
                changeScene(event, "LogOut.fxml", "Log Out", phonenumber);
                System.out.println("after");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // ...
    }
    public static void logInUser(ActionEvent event, String phonenumber, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/docdirect", "root", "root");
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE phonenumber=? AND password=?");
            preparedStatement.setString(1, phonenumber);
            resultset = preparedStatement.executeQuery();
            if (!resultset.isBeforeFirst()) {
                System.out.println("User does not exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User does not exist");
                alert.show();
            } else {
                while (resultset.next()) {
                    String retrievedPassword = resultset.getString("password");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "LogOut.fxml", "Welcome", resultset.getString("name"));
                    } else {
                        System.out.println("Passwords did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Passwords did not match");
                        alert.show();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

