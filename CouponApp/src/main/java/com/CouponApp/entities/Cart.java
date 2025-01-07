package com.CouponApp.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;
    private double totalValue;
    private double discountedTotal;

    public Cart() {
        this.items = new ArrayList<>();
        this.totalValue = 0.0;
        this.discountedTotal = 0.0;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
        updateTotal();
    }

    public double getTotalValue() {
        return totalValue;
    }

    public double getDiscountedTotal() {
        return discountedTotal;
    }

    public void setDiscountedTotal(double discountedTotal) {
        this.discountedTotal = discountedTotal;
    }

    public void addFreeItem(CartItem freeItem) {
        this.items.add(freeItem);
    }

    public void updateTotal() {
        totalValue = 0.0;
        for (CartItem item : items) {
            totalValue += item.getPrice() * item.getQuantity();
        }
    }
}
