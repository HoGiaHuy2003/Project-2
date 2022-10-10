/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import java.util.List;
import java.util.Vector;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class Order {

    private int customerId;
    private int staffId;
    private int orderId;
    private String customerName;
    private String customerPhoneNumber;
    private Integer productId;
    private String productName;
    private Integer numberOfProduct;
    private Float priceOfProduct;
    private Float totalPrice;
    private static String orderDate;
    private String orderdate;
    private static List<Order> orderList = new Vector<>();

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public static List<Order> getOrderList() {
        return orderList;
    }

    public static void setOrderList(List<Order> orderList) {
        Order.orderList = orderList;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }
    
    

    public Order() {
    }

    public Order(int customerId, int staffId) {
        this.customerId = customerId;
        this.staffId = staffId;
    }

    public Order(Integer productId, Integer numberOfProduct, Float priceOfProduct) {
        this.productId = productId;
        this.numberOfProduct = numberOfProduct;
        this.priceOfProduct = priceOfProduct;
    }

    public Order(int orderId, Integer productId, Integer numberOfProduct, Float priceOfProduct, String orderdate) {
        this.orderId = orderId;
        this.productId = productId;
        this.numberOfProduct = numberOfProduct;
        this.priceOfProduct = priceOfProduct;
        this.orderdate = orderdate;
    }
    
    public Order(int customerId, int staffId, String customerName, String customerPhoneNumber, Integer productId, String productName, Integer numberOfProduct, Float priceOfProduct) {
        this.customerId = customerId;
        this.staffId = staffId;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.productId = productId;
        this.productName = productName;
        this.numberOfProduct = numberOfProduct;
        this.priceOfProduct = priceOfProduct;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getNumberOfProduct() {
        return numberOfProduct;
    }

    public void setNumberOfProduct(Integer numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public Float getPriceOfProduct() {
        return priceOfProduct;
    }

    public void setPriceOfProduct(Float priceOfProduct) {
        this.priceOfProduct = priceOfProduct;
    }

    public Float getTotalPriceOfProduct() {
        totalPrice = numberOfProduct * priceOfProduct;
        return totalPrice;
    }
    
    public static Float getTotalPrice(){
        float totalPrice = 0;
        for(int i = 0; i < Order.getOrderList().size(); i++){
            totalPrice += Order.getOrderList().get(i).getTotalPriceOfProduct();
        }
        return totalPrice;
    }

    public static String getOrderDate() {
        return orderDate;
    }

    public static void setOrderDate(String orderDate) {
        Order.orderDate = orderDate;
    }

}
