package com.CouponApp.services;

import com.CouponApp.entities.Coupon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    public static class Cart {
        private List<Item> items;

        public Cart(List<Item> items) {
            this.items = items;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public static class Item {
            private String productId;
            private double price;
            private int quantity;

            // Constructor, getters, and setters
            public Item(String productId, double price, int quantity) {
                this.productId = productId;
                this.price = price;
                this.quantity = quantity;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }
        }
    }


    private List<Coupon> couponList = new ArrayList<>();

    // Apply the coupon to the cart
    public Cart applyCouponToCart(long couponId, Cart cart) {
        // Here, add your logic for applying the coupon based on the couponId
        Coupon coupon = getCouponById(couponId); // Retrieve coupon by ID (you can implement this method)
        if (coupon != null) {
            double totalDiscount = 0.0;
            for (Cart.Item item : cart.getItems()) {

                double discount = item.getPrice() * (coupon.getDiscount() / 100);
                item.setPrice(item.getPrice() - discount);
                totalDiscount += discount;
            }

            return cart;
        }
        return null;
    }


    public List<Coupon> getApplicableCoupons(Cart cart) {
        List<Coupon> applicableCoupons = new ArrayList<>();
        for (Coupon coupon : couponList) {
            // Apply your logic to check if the coupon is applicable to the cart
            if (cart.getItems().stream().mapToDouble(item -> item.getPrice()).sum() > coupon.getMinCartValue()) {
                applicableCoupons.add(coupon);
            }
        }
        return applicableCoupons;
    }

    // Dummy method to simulate fetching coupon by ID
    private Coupon getCouponById(long id) {
        return couponList.stream()
                .filter(coupon -> coupon.getId() == id)
                .findFirst()
                .orElse(null);
    }



}
