/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import com.mycompany.models.Staff;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class StaffEntity extends BaseEntity {

    public static void insert(Staff newaccount) throws NoSuchAlgorithmException {
        open();

        try {
            String sql = "INSERT INTO Staff(role_id, fullname, birthday, gender, address, phone_number, email, password, created_at, updated_at) " + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, newaccount.getRoleId());
            statement.setString(2, newaccount.getFullname());
            statement.setString(3, newaccount.getBirthday());
            statement.setString(4, newaccount.getGender());
            statement.setString(5, newaccount.getAddress());
            statement.setString(6, newaccount.getPhonenumber());
            statement.setString(7, newaccount.getEmail());
            statement.setString(8, newaccount.getPassword());
            statement.setString(9, newaccount.getCreatedat());
            statement.setString(10, newaccount.getUpdatedat());

            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(StaffEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }

    public static void update(Staff newaccount) {

    }

    public static void delete(int id) {

    }

    public static Staff findId(int role_id) {
        Staff staff = null;
        open();

        String sql = "SELECT * FROM Staff WHERE id = ?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, role_id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                staff = new Staff(resultSet.getInt("id"), resultSet.getInt("role_id"), resultSet.getString("fullname"), resultSet.getString("birthday"), resultSet.getString("gender"), resultSet.getString("address"), resultSet.getString("phone_number"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("created_at"), resultSet.getString("updated_at"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return staff;
    }

    public static Staff login(Staff staff) throws NoSuchAlgorithmException {
        open();

        String sql = "SELECT * FROM Staff WHERE email = ? OR phone_number = ? AND password = ?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, staff.getEmail());
            statement.setString(2, staff.getPhonenumber());
            statement.setString(3, staff.getPassword());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                staff = new Staff(resultSet.getString("phone_number"), resultSet.getString("email"), resultSet.getString("password"));
                staff.setId(resultSet.getInt("id"));
                staff.setRoleId(resultSet.getInt("role_id"));
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

        return staff;
    }

    public static ObservableList<Staff> employerList() {
//        List<Staff> list = new Vector<>();
        ObservableList<Staff> list = FXCollections.observableArrayList();
        open();

        String sql = "SELECT Role.name 'role', Staff.fullname, Staff.birthday, Staff.gender, Staff.address, Staff.phone_number, Staff.email, Staff.created_at FROM Staff LEFT JOIN Role ON Staff.role_id = Role.id WHERE Role.name = 'Employer'";

        try {
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Staff staff = new Staff(resultSet.getString("role"), resultSet.getString("fullname"), resultSet.getString("birthday"), resultSet.getString("gender"), resultSet.getString("address"), resultSet.getString("phone_number"), resultSet.getString("email"), resultSet.getString("created_at"));
//                staff.setFullname(resultSet.getString("fullname"));
//                staff.setBirthday(resultSet.getString("birthday"));
//                staff.setGender(resultSet.getString("gender"));
//                staff.setAddress(resultSet.getString("address"));
//                staff.setPhoneNumber(resultSet.getString("phone_number"));
//                staff.setEmail(resultSet.getString("email"));
//                staff.setCreatedAt(resultSet.getString("created_at"));
//                staff.setRoleName(resultSet.getString("role"));
                list.add(staff);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

//        ObservableList<Staff> dataList = FXCollections.observableList(list);
        return list;
    }

    public static ObservableList<Staff> employeeList() {
//        List<Staff> list = new Vector<>();
        ObservableList<Staff> list = FXCollections.observableArrayList();
        open();

        String sql = "SELECT Role.name 'role', Staff.fullname, Staff.birthday, Staff.gender, Staff.address, Staff.phone_number, Staff.email, Staff.created_at FROM Staff LEFT JOIN Role ON Staff.role_id = Role.id WHERE Role.name = 'Employee'";

        try {
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Staff staff = new Staff(resultSet.getString("role"), resultSet.getString("fullname"), resultSet.getString("birthday"), resultSet.getString("gender"), resultSet.getString("address"), resultSet.getString("phone_number"), resultSet.getString("email"), resultSet.getString("created_at"));
//                staff.setFullname(resultSet.getString("fullname"));
//                staff.setBirthday(resultSet.getString("birthday"));
//                staff.setGender(resultSet.getString("gender"));
//                staff.setAddress(resultSet.getString("address"));
//                staff.setPhoneNumber(resultSet.getString("phone_number"));
//                staff.setEmail(resultSet.getString("email"));
//                staff.setCreatedAt(resultSet.getString("created_at"));
//                staff.setRoleName(resultSet.getString("role"));
                list.add(staff);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

//        ObservableList<Staff> dataList = FXCollections.observableList(list);
        return list;
    }

}
