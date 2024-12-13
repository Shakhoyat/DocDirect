package com.example.dox.docdirect;

public class ServiceClass {
    private String serviceName;
    private int quantity;
    private double price;

    // Constructor
    public ServiceClass(String serviceName, int quantity, double price) {
        this.serviceName = serviceName;
        this.quantity = quantity;
        this.price = price;
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
