/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import com.mycompany.models.Product;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ProductEntity extends BaseEntity {

    public static void insert(Product product) {
        open();

        try {
            String sql = "INSERT INTO Product (category_id, title, price, quantity, seleable_number, description, thumbnail, created_at, updated_at), VALUES(?, ?, ?, ?, 0, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, product.getCategoryId());
            statement.setString(2, product.getTitie());
            statement.setFloat(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
//            statement.setInt(5, product.getSeleableNumber());
            statement.setString(5, product.getDescription());
            statement.setString(6, product.getThumbnail());
            statement.setString(7, product.getCreatedAt());
            statement.setString(8, product.getUpdatedAt());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProductEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }

    public static void updateSelebaleNumber(Product product) {
        open();

        try {
            String sql = "UPDATE Product SET seleable_number = seleable_number + 1 WHERE id = ?";
            statement = conn.prepareStatement(sql);
            
            statement.setInt(1, product.getId());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProductEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }
}
