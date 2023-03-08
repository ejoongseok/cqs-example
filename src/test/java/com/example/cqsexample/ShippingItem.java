package com.example.cqsexample;

import java.util.ArrayList;
import java.util.List;

public class ShippingItem {
    List<Picking> pickings = new ArrayList<>();
    private final String productSerialNumber;
    private final int orderQuantity;

    public ShippingItem(final String productSerialNumber, final int orderQuantity) {
        this.productSerialNumber = productSerialNumber;
        this.orderQuantity = orderQuantity;
    }


    public String getProductSerialNumber() {
        return productSerialNumber;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public List<Picking> getPickings() {
        return pickings;
    }

    @Override
    public String toString() {
        return "ShippingItem{" +
                "productSerialNumber='" + productSerialNumber + '\'' +
                ", orderQuantity=" + orderQuantity +
                ", pickings=" + pickings +
                '}';
    }

    public void assignPickings(final List<Picking> pickings) {
        this.pickings.addAll(pickings);
    }

    public void assignPicking(final Picking picking) {
        pickings.add(picking);
    }
}
