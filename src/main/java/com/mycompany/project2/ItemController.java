/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.project2;

import com.mycompany.models.Product;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ItemController implements Initializable {

    @FXML
    private Label title;

    @FXML
    private Label price;

    @FXML
    private ImageView imageView;

    private Product product;
    
    private Image image;

    void setData(Product product) {
        this.product = product;
        title.setText(product.getTitle());
        price.setText("$" + product.getPrice());
        File file = new File(product.getThumbnail());
        if (file.isFile()) {
            image = new Image(file.toURI().toString());
        } else {
            image = new Image(product.getThumbnail(), true);
        }
        imageView.setImage(image);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
