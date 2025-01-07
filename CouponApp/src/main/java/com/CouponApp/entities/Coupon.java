package com.CouponApp.entities;

public class Coupon {
    private Long id; // Unique identifier for the coupon
    private String type; // cart-wise, product-wise, BxGy
    private Double discount; // Discount percentage (for cart-wise or product-wise)
    private Double minCartValue; // Minimum cart value for cart-wise
    private String applicableProductIds; // Comma-separated product IDs for product-wise
    private Integer buyX; // For BxGy
    private Integer getY; // For BxGy
    private String freeProductIds; // Comma-separated free products for BxGy

    public Coupon() {
    }
    public Coupon(Long id, String type, Double discount, Double minCartValue,
                  String applicableProductIds, Integer buyX, Integer getY, String freeProductIds) {
        this.id = id;
        this.type = type;
        this.discount = discount;
        this.minCartValue = minCartValue;
        this.applicableProductIds = applicableProductIds;
        this.buyX = buyX;
        this.getY = getY;
        this.freeProductIds = freeProductIds;
    }

    public Coupon(int i, String type, double discount, double minCartValue) {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getMinCartValue() {
        return minCartValue;
    }

    public void setMinCartValue(Double minCartValue) {
        this.minCartValue = minCartValue;
    }

    public String getApplicableProductIds() {
        return applicableProductIds;
    }

    public void setApplicableProductIds(String applicableProductIds) {
        this.applicableProductIds = applicableProductIds;
    }

    public Integer getBuyX() {
        return buyX;
    }

    public void setBuyX(Integer buyX) {
        this.buyX = buyX;
    }

    public Integer getGetY() {
        return getY;
    }

    public void setGetY(Integer getY) {
        this.getY = getY;
    }

    public String getFreeProductIds() {
        return freeProductIds;
    }

    public void setFreeProductIds(String freeProductIds) {
        this.freeProductIds = freeProductIds;
    }
}
