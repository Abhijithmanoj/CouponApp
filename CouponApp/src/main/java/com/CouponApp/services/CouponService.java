package com.CouponApp.services;

import com.CouponApp.entities.Cart;
import com.CouponApp.entities.CartItem;
import com.CouponApp.entities.Coupon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CouponService {

    private final Map<Long, Coupon> couponStorage = new ConcurrentHashMap<>();
    private long couponIdCounter = 1L;

    public Coupon createCoupon(Coupon coupon) {
        coupon.setId(couponIdCounter++);
        couponStorage.put(coupon.getId(), coupon);
        return coupon;
    }

    public List<Coupon> getAllCoupons() {
        return new ArrayList<>(couponStorage.values());
    }

    public Coupon getCouponById(Long id) {
        return couponStorage.get(id);
    }

    public Coupon updateCoupon(Long id, Coupon coupon) {
        if (!couponStorage.containsKey(id)) {
            return null;
        }
        coupon.setId(id);
        couponStorage.put(id, coupon);
        return coupon;
    }

    public void deleteCoupon(Long id) {
        couponStorage.remove(id);
    }

    public List<Coupon> getApplicableCoupons(Cart cart) {
        List<Coupon> applicableCoupons = new ArrayList<>();

        for (Coupon coupon : couponStorage.values()) {
            if (isCouponApplicable(cart, coupon)) {
                applicableCoupons.add(coupon);
            }
        }
        return applicableCoupons;
    }

    public Cart applyCoupon(Long couponId, Cart cart) {
        Coupon coupon = couponStorage.get(couponId);
        if (coupon == null || !isCouponApplicable(cart, coupon)) {
            return null;
        }

        switch (coupon.getType().toLowerCase()) {
            case "cart-wise":
                applyCartWiseDiscount(cart, coupon);
                break;
            case "product-wise":
                applyProductWiseDiscount(cart, coupon);
                break;
            case "bxgy":
                applyBxGyDiscount(cart, coupon);
                break;
            default:
                return null;
        }

        return cart;
    }

    private boolean isCouponApplicable(Cart cart, Coupon coupon) {
        switch (coupon.getType().toLowerCase()) {
            case "cart-wise":
                return cart.getTotalValue() >= coupon.getMinCartValue();
            case "product-wise":
                return cartContainsApplicableProducts(cart, coupon);
            case "bxgy":
                return cartContainsRequiredBxGyItems(cart, coupon);
            default:
                return false;
        }
    }

    private void applyCartWiseDiscount(Cart cart, Coupon coupon) {
        double discount = cart.getTotalValue() * (coupon.getDiscount() / 100);
        cart.setDiscountedTotal(cart.getTotalValue() - discount);
    }

    private void applyProductWiseDiscount(Cart cart, Coupon coupon) {
        for (CartItem item : cart.getItems()) {
            if (isProductInCoupon(item.getProductId(), coupon.getApplicableProductIds())) {
                double discount = item.getPrice() * (coupon.getDiscount() / 100);
                item.setDiscountedPrice(item.getPrice() - discount);
            }
        }
        cart.updateTotal();
    }

    private void applyBxGyDiscount(Cart cart, Coupon coupon) {
        int eligibleSets = calculateBxGyEligibleSets(cart, coupon.getBuyX(), coupon.getApplicableProductIds());
        if (eligibleSets > 0) {
            String[] freeProducts = coupon.getFreeProductIds().split(",");
            for (String freeProduct : freeProducts) {
                cart.addFreeItem(new CartItem(freeProduct, 0.0, eligibleSets));
            }
        }
        cart.updateTotal();
    }

    private boolean cartContainsApplicableProducts(Cart cart, Coupon coupon) {
        for (CartItem item : cart.getItems()) {
            if (isProductInCoupon(item.getProductId(), coupon.getApplicableProductIds())) {
                return true;
            }
        }
        return false;
    }

    private boolean cartContainsRequiredBxGyItems(Cart cart, Coupon coupon) {
        int count = 0;
        for (CartItem item : cart.getItems()) {
            if (isProductInCoupon(item.getProductId(), coupon.getApplicableProductIds())) {
                count += item.getQuantity();
            }
        }
        return count >= coupon.getBuyX();
    }

    private boolean isProductInCoupon(String productId, String applicableProducts) {
        String[] products = applicableProducts.split(",");
        for (String product : products) {
            if (product.equals(productId)) {
                return true;
            }
        }
        return false;
    }

    private int calculateBxGyEligibleSets(Cart cart, int buyX, String applicableProducts) {
        int count = 0;
        for (CartItem item : cart.getItems()) {
            if (isProductInCoupon(item.getProductId(), applicableProducts)) {
                count += item.getQuantity();
            }
        }
        return count / buyX;
    }
}
