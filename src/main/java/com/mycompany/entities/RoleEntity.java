/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import static com.mycompany.entities.BaseEntity.conn;
import com.mycompany.models.Role;
import java.sql.DriverManager;
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
public class RoleEntity extends BaseEntity {

    public static ObservableList<Role> getRoleList() {
        List<Role> list = new Vector<>();

        open();
        
        String sql = "SELECT * FROM role";
        
        try {
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Role role = new Role(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("password"));
                list.add(role);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoleEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

        ObservableList<Role> dataList = FXCollections.observableList(list);

        return dataList;
    }
}
