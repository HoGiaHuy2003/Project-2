/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import com.mycompany.models.Register;
import com.mycompany.models.Role;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class RegisterEntity extends BaseEntity {

    public static void insert(Register newaccount) throws NoSuchAlgorithmException {
        open();

        try {
            String sql = "INSERT INTO Staff(role_id, fullname, birthday, gender, address, phone_number, email, password, created_at, updated_at) " + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, newaccount.getRoleId());
            statement.setString(2, newaccount.getFullname());
            statement.setString(3, newaccount.getBirthday());
            statement.setString(4, newaccount.getGender());
            statement.setString(5, newaccount.getAddress());
            statement.setString(6, newaccount.getPhoneNumber());
            statement.setString(7, newaccount.getEmail());
            statement.setString(8, newaccount.getPassword());
            statement.setString(9, newaccount.getCreatedAt());
            statement.setString(10, newaccount.getUpdatedAt());

            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(RegisterEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }

    public static void update(Register newaccount) {

    }

    public static void delete(int id) {

    }

    public static Register find(Register newaccount) throws NoSuchAlgorithmException {
        open();
        
        String sql = "SELECT * FROM Staff WHERE email = ? AND password = ?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, newaccount.getEmail());
            statement.setString(2, newaccount.getPassword());
            
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                newaccount = new Register(resultSet.getString("email"), resultSet.getString("password"));
                break;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RegisterEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        
        return newaccount;
    }

    public static List<Register> list() {
        return null;
    }
}
