/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

/**
 *
 * @author Admin
 */
public class Product extends Category {

    private int id;
    private String title;
    private float price;
    private int quantity;
    private int seleableNumber;
    private String description;
    private String thumbnail;
    private String createdAt;
    private String updatedAt;
    private static int editProductById;

    public static int getEditProductById() {
        return editProductById;
    }

    public static void setEditProductById(int editProductById) {
        Product.editProductById = editProductById;
    }

    public Product() {
    }

    public Product(String title, float price, String thumbnail) {
        this.title = title;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public Product(int id, int categoryId, String title, float price, int quantity, int seleableNumber, String description, String thumbnail, String createdAt, String updatedAt) {
        super(categoryId);
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.seleableNumber = seleableNumber;
        this.description = description;
        this.thumbnail = thumbnail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Product(int categoryId, String title, float price, int quantity, String description, String thumbnail, String createdAt, String updatedAt) {
        super(categoryId);
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.thumbnail = thumbnail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSeleableNumber() {
        return seleableNumber;
    }

    public void setSeleableNumber(int seleableNumber) {
        this.seleableNumber = seleableNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
