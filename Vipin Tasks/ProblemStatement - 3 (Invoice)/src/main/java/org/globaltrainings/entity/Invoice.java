package org.globaltrainings.entity;

import java.util.Objects;

public class Invoice {
    private String partNumber;
    private String partDescription;
    private int quantity;
    private double pricePerItem;

    public Invoice(){}
    public Invoice(String partNumber, String partDescription, int quantity, double pricePerItem) {
        this.partNumber = partNumber;
        this.partDescription = partDescription;

        if(quantity<0){
            this.quantity =0;
        }
        else{
            this.quantity = quantity;
        }
        if(pricePerItem<0){
            this.pricePerItem = 0;
        }
        else {
            this.pricePerItem = pricePerItem;
        }
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity<0){
            this.quantity =0;
        }
        else{
            this.quantity = quantity;
        }
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(double pricePerItem) {
        if(pricePerItem<0){
            this.pricePerItem = 0;
        }
        else {
            this.pricePerItem = pricePerItem;
        }
    }

    public double getInvoiceAmount(){
        return quantity*pricePerItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(partNumber, invoice.partNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partNumber);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "partNumber='" + partNumber + '\'' +
                ", partDescription='" + partDescription + '\'' +
                ", quantity=" + quantity +
                ", pricePerItem=" + pricePerItem +
                '}';
    }
}
