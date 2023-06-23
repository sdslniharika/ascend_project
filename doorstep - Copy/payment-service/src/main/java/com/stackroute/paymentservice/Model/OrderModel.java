package com.stackroute.paymentservice.Model;




import javax.persistence.Id;
import java.math.BigInteger;

public class OrderModel {

    @Id
    private BigInteger orderId;
    private int customerId;
    private int providerId;
    private String customerName;
    private String customerAddress;
    //private List<Item> itemList;
    private String paymentMode;

    private double priceTotal;

    public OrderModel() {
    }

    public OrderModel(BigInteger orderId, int customerId, int providerId, String customerName, String customerAddress, String paymentMode, double priceTotal) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.providerId = providerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.paymentMode = paymentMode;
        this.priceTotal = priceTotal;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }
}



