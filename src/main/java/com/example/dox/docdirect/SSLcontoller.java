package com.example.dox.docdirect;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SSLcontoller {
    @FXML
    private WebView webView;

    private static final String STORE_ID = "docdi675ca29fc4929";
    private static final String STORE_PASSWORD = "docdi675ca29fc4929@ssl";
    private static final String TRANSACTION_URL = "https://sandbox.sslcommerz.com/gwprocess/v4/api.php";

    private static final String DB_URL = "jdbc:mysql://localhost:3306/DocDirectDB";
    private static final String DB_USERNAME = "skt_pie";
    private static final String DB_PASSWORD = "12104053";

    private String createPaymentSession(double amount) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            Map<String, String> params = new HashMap<>();
            params.put("store_id", STORE_ID);
            params.put("store_passwd", STORE_PASSWORD);
            params.put("total_amount", String.valueOf(amount));
            params.put("currency", "BDT");
            params.put("tran_id", "TEST_" + System.currentTimeMillis());
            params.put("success_url", "http://127.0.0.1:1000/success_done");
            params.put("fail_url", "http://127.0.0.1:1000/faildone");
            params.put("cancel_url", "http://127.0.0.1:1000/canceldone");
            params.put("cus_name", "Test Customer");
            params.put("cus_email", "test@example.com");
            params.put("cus_add1", "Test Address");
            params.put("cus_city", "Dhaka");
            params.put("cus_postcode", "1207");
            params.put("cus_country", "Bangladesh");
            params.put("cus_phone", "01700000000");
            params.put("shipping_method", "NO");
            params.put("product_name", "DocDirect Payment Gateway Testing - Java SDK - " + System.currentTimeMillis() );
            params.put("product_category", "service");
            params.put("product_profile", "general");

            String form = params.entrySet().stream()
                    .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" +
                            URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(TRANSACTION_URL))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(form))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String responseJson = response.body();
            System.out.println(responseJson);
            if (responseJson.contains("\"GatewayPageURL\"")) {
                int startIndex = responseJson.indexOf("\"GatewayPageURL\":\"") + 18;
                int endIndex = responseJson.indexOf("\"", startIndex);
                return responseJson.substring(startIndex, endIndex).replace("\\/", "/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void handleProceedToCheckout(double amount) {
        try {
            String paymentUrl = createPaymentSession(amount);
            if (paymentUrl != null) {
                webView.getEngine().load(paymentUrl);
            } else {
                showAlert("Failed", "Failed to create payment request.");
                return;
            }
            webView.getEngine().locationProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.contains("success_done")) {
                    webView.getEngine().loadContent("");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Payment");
                    alert.setHeaderText(null);
                    alert.setContentText("Payment Completed Successfully!");
                    alert.showAndWait();

                    saveDetails();
                    Stage stage = (Stage) webView.getScene().getWindow();
                    stage.close();
                    Stage stage1 = new Stage();
                    try {
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Myfxmls/myCart/viewCart.fxml")));
                        stage1.setScene(scene);
                        stage1.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while processing the payment.");
        }
    }

    private void saveDetails() {
        final String query = "UPDATE user_" + CurrentUser.phoneNumber + "_appointments SET payment_status = 'PAID' WHERE payment_status = 'UNPAID'";

        try (Connection connectDB = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connectDB.createStatement();) {

            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        final String query2 = "UPDATE user_" + CurrentUser.phoneNumber + "_services SET payment_status = 'PAID' WHERE payment_status = 'UNPAID'";

        try (Connection connectDB = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connectDB.createStatement();) {

            statement.execute(query2);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
