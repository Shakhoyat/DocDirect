package com.example.dox.docdirect;

public class ServiceClass {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String serviceName;
    private int quantity;
    private double price;

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    private  String payment_status;
    // Constructor
    public ServiceClass(int id, String serviceName, int quantity, double price, String payment_status) {
        this.id = id;
        this.serviceName = serviceName;
        this.quantity = quantity;
        this.price = price;
        this.payment_status=payment_status;
    }

    // Getters and Setters
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
