package com.CouponApp.entities;

public class CartItem {
    private String productId;
    private double price;
    private double discountedPrice;
    private int quantity;

    public CartItem(String productId, double price, int quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.discountedPrice = price;
    }

    public String getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
