/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import com.mycompany.models.Product;
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
public class ProductEntity extends BaseEntity {

    public static void insert(Product product) {
        open();

        try {
            String sql = "INSERT INTO Product (category_id, title, price, quantity, seleable_number, description, thumbnail, created_at, updated_at) VALUES (?, ?, ?, ?, 0, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, product.getCategoryId());
            statement.setString(2, product.getTitle());
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

    public static void update(Product product) {
        open();
        try {
            String sql = "UPDATE Product SET category_id = ?, title = ?, price = ?, quantity = ?, description = ?, thumbnail = ?, updated_at = ? WHERE id = ?";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, product.getCategoryId());
            statement.setString(2, product.getTitle());
            statement.setFloat(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setString(5, product.getDescription());
            statement.setString(6, product.getThumbnail());
            statement.setString(7, product.getUpdatedAt());
            statement.setInt(8, product.getId());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProductEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }

    public static Product findProduct(int id) {
        Product product = null;
        open();

        try {
            String sql = "SELECT * FROM Product WHERE id = ?";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                product = new Product(resultSet.getInt("id"), resultSet.getInt("category_id"), resultSet.getString("title"), resultSet.getFloat("price"), resultSet.getInt("quantity"), resultSet.getInt("seleable_number"), resultSet.getString("description"), resultSet.getString("thumbnail"), resultSet.getString("created_at"), resultSet.getString("updated_at"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

        return product;
    }

    public static List<Product> productList() {
        List<Product>productList = new Vector<>();

        open();

        try {
            String sql = "SELECT * FROM Product";
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("title"), resultSet.getFloat("price"), resultSet.getString("thumbnail"));
                productList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        
        ObservableList<Product> dataList = FXCollections.observableList(productList);
        
        return dataList;
    }
}
