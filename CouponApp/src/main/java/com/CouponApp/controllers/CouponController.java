package com.CouponApp.controllers;

import com.CouponApp.entities.Cart;
import com.CouponApp.entities.Coupon;
import com.CouponApp.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponController {
    @Autowired
    private CouponService couponService;
    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        return ResponseEntity.ok(couponService.createCoupon(coupon));
    }

    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        return ResponseEntity.ok(couponService.getAllCoupons());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Coupon> getCoupon(@PathVariable Long id) {
        Coupon coupon = couponService.getCouponById(id);
        return coupon != null ? ResponseEntity.ok(coupon) : ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Coupon> updateCoupon(@PathVariable Long id, @RequestBody Coupon coupon) {
        return ResponseEntity.ok(couponService.updateCoupon(id, coupon));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/applicable-coupons")
    public ResponseEntity<List<Coupon>> getApplicableCoupons(@RequestBody Cart cart) {
        return ResponseEntity.ok(couponService.getApplicableCoupons(cart));
    }

    @PostMapping("/apply-coupon/{id}")
    public ResponseEntity<Cart> applyCoupon(@PathVariable Long id, @RequestBody Cart cart) {
        Cart updatedCart = couponService.applyCoupon(id, cart);
        return updatedCart != null ? ResponseEntity.ok(updatedCart) : ResponseEntity.badRequest().build();
    }
}
