# CouponApp
Overview

This project implements a REST API for managing discount coupons and applying them to customer shopping carts. The API supports multiple coupon types and provides endpoints for creating, updating, retrieving, and applying coupons.

Key Features
CRUD Operations for Coupons
Dynamic Discount Logic:
Cart-wise discounts.
Product-specific discounts.
Buy X Get Y Free (BxGy) promotions.
Cart Management:
Calculate applicable coupons for a cart.
Apply discounts to the cart and update totals dynamically.

Tech Stack
Java 17
Spring Boot (RESTful API development)
ConcurrentHashMap (In-memory storage for demo purposes)
Maven (Build tool)

Endpoints
Coupon Management
Create Coupon:POST /coupons
Request Body:
{
  "type": "cart-wise",
  "discount": 10,
  "minCartValue": 100,
  "applicableProductIds": "",
  "expirationDate": "2025-01-31"
}

Get All Coupons:GET /coupons
Get Coupon by ID:GET /coupons/{id}
Update Coupon:PUT /coupons/{id}
Delete Coupon:DELETE /coupons/{id}
Cart Operations
Get Applicable Coupons:POST /coupons/applicable-coupons
Request Body:
{
  "items": [
    { "productId": "p1", "price": 100, "quantity": 2 }
  ]
}
Apply Coupon to Cart:POST /coupons/apply-coupon/{id}

Running the Application
Clone the Repository:
git clone https://github.com/your-repo/coupon-management-api.git
cd couponApp
Build the Project:mvn clean install
Run the Application:mvn spring-boot:run

Access the API:
Base URL: http://localhost:8080
