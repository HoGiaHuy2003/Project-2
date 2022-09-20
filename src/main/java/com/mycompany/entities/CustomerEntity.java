/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import com.mycompany.models.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class CustomerEntity extends BaseEntity {

    public static void insert(Customer customer) {
        open();

        try {
            String sql = "INSERT INTO customer(fullname, birthday, gender, address, phone_number, email, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);

            statement.setString(1, customer.getFullname());
            statement.setString(2, customer.getBirthday());
            statement.setString(3, customer.getGender());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getPhoneNumber());
            statement.setString(6, customer.getEmail());
            statement.setString(7, customer.getCreatedAt());
            statement.setString(8, customer.getUpdatedAt());

            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }

    public static void update(Customer customer) {
        open();

        try {
            String sql = "UPDATE customer SET fullname = ?, birthday = ?, gender = ?, address = ?, phone_number = ?, email = ?, updated_at = ? WHERE id = ?";
            statement = conn.prepareStatement(sql);

            statement.setString(1, customer.getFullname());
            statement.setString(2, customer.getBirthday());
            statement.setString(3, customer.getGender());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getPhoneNumber());
            statement.setString(6, customer.getEmail());
            statement.setString(7, customer.getCreatedAt());
            statement.setInt(8, customer.getId());
            
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static Customer findCustomerId(int customer_id) {
        Customer customer = null;
        open();

        String sql = "SELECT * FROM customer WHERE id = ?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, customer_id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customer = new Customer(resultSet.getInt("id"), resultSet.getString("fullname"), resultSet.getString("birthday"), resultSet.getString("gender"), resultSet.getString("address"), resultSet.getString("phone_number"), resultSet.getString("email"), resultSet.getString("created_at"), resultSet.getString("updated_at"));
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return customer;
    }

    public static ObservableList<Customer> customerList() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        open();

        try {
            String sql = "SELECT * FROM customer";
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getInt("id"), resultSet.getString("fullname"), resultSet.getString("birthday"), resultSet.getString("gender"), resultSet.getString("address"), resultSet.getString("phone_number"), resultSet.getString("email"), resultSet.getString("created_at"), resultSet.getString("updated_at"));
                customerList.add(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

        return customerList;
    }
}
