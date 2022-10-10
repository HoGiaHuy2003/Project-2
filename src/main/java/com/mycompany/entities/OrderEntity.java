/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import com.mycompany.models.Customer;
import com.mycompany.models.Order;
import com.mycompany.models.Staff;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class OrderEntity extends BaseEntity {

    public static void insert() {
        open();
        try {
            String sql = "INSERT INTO Order_(customer_id, staff_id) VALUES(?, ?)";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, Customer.getValueOfCustomerId());
            statement.setInt(2, Staff.getLoginStaffId());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }

    public static List<Order> orderList() {
        List<Order> orderList = new Vector<>();
        open();
        try {
            String sql = "SELECT * FROM Order_";
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order(resultSet.getInt("customer_id"), resultSet.getInt("staff_id"));
                orderList.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return orderList;
    }

    public static Order findOrderId() {
        Order order = null;
        open();
        try {
            String sql = "SELECT * FROM Order_ WHERE customer_id = ? AND staff_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, Customer.getValueOfCustomerId());
            statement.setInt(2, Staff.getLoginStaffId());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = new Order();
                order.setOrderId(resultSet.getInt("id"));
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

        return order;
    }

    public static void insertOrder(Order order) {
        open();
        try {
            String sql = "INSERT INTO order_detail(order_id, product_id, price, quantity, order_date) VALUES (?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql); 
            statement.setInt(1, order.getOrderId());
            statement.setInt(2, order.getProductId());
            statement.setFloat(3, order.getPriceOfProduct());
            statement.setInt(4, order.getNumberOfProduct());
            statement.setString(5, order.getOrderdate());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }
}
