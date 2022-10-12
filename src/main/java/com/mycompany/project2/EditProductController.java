/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.project2;

import com.mycompany.entities.CategoryEntity;
import com.mycompany.entities.ProductEntity;
import com.mycompany.models.Category;
import com.mycompany.models.Product;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLStreamHandlerFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class EditProductController implements Initializable {

    @FXML
    private Label title;

    @FXML
    private ComboBox cbCategory;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtThumbnail;

    @FXML
    private ImageView thumbnail;

    private Image image;
    
    private Product product = ProductEntity.findProduct(Product.getEditProductById());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setUpComboBox();
        if(Product.getEditProductById() != 0){
            title.setText("Edit Product");
            cbCategory.setValue(product.getCategoryName());
            txtTitle.setText(product.getTitle());
            txtPrice.setText(product.getPrice().toString());
            txtQuantity.setText(product.getQuantity().toString());
            txtDescription.setText(product.getDescription());
            txtThumbnail.setText(product.getThumbnail());
        }
    }

    @FXML
    private void getThumbnail() {
        try {
            File file = new File(txtThumbnail.getText());
            if (file.isFile()) {
                image = new Image(file.toURI().toString());
            }
//             else {
//                image = new Image(txtThumbnail.getText(), true);
//            }
            if (!image.isError()) {
                thumbnail.setImage(image);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirm!!!");
                alert.setHeaderText("Find picture successfully");
                alert.setContentText("Your picture is successfully found");
                alert.showAndWait();
            }
        } catch (IllegalArgumentException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error parsing file: ");
            alert.setHeaderText("Cannot find URL: ");
            alert.showAndWait();
        }
    }

    private void setUpComboBox() {
        List<Category> categoryList = CategoryEntity.getCategoryList();
        for (int i = 0; i < categoryList.size(); i++) {
            cbCategory.getItems().addAll(categoryList.get(i).getCategoryName());
            cbCategory.getSelectionModel().select(null);
        }
    }

    @FXML
    private void switchToManageProduct(MouseEvent event) throws IOException {
        App.setRoot("product");
    }

    @FXML
    private void save(ActionEvent event) throws IOException {
        try {
            if (image == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cannot find image: ");
                alert.setHeaderText("Image must not be null!!!");
                alert.showAndWait();
                return;
            }
            String title = txtTitle.getText().toString();
            float price = Float.parseFloat(txtPrice.getText().toString());
            int quantity = Integer.parseInt(txtQuantity.getText().toString());
            String description = txtDescription.getText().toString();
            String thumbnail = txtThumbnail.getText().toString();
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String createdAt = dateFormat.format(date);
            String updatedAt = createdAt;
            List<Category> categoryList = CategoryEntity.getCategoryList();
            if (Product.getEditProductById() == 0) {
                for (int i = 0; i < categoryList.size(); i++) {
                    if (categoryList.get(i).getCategoryName().equals(cbCategory.getValue())) {
                        int categoryId = categoryList.get(i).getCategoryId();
                        Product product = new Product(Product.getEditProductById(), categoryId, title, price, quantity, description, thumbnail, createdAt, updatedAt);
                        ProductEntity.insert(product);
                        App.setRoot("product");
                        break;
                    }
                }
            } else {
                for (int i = 0; i < categoryList.size(); i++) {
                    if (categoryList.get(i).getCategoryName().equals(cbCategory.getValue())) {
                        int categoryId = categoryList.get(i).getCategoryId();
                        Product product = new Product(Product.getEditProductById() ,categoryId, title, price, quantity, description, thumbnail, createdAt, updatedAt);
                        ProductEntity.update(product);
                        App.setRoot("product");
                        break;
                    }
                }
            }
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error number format: ");
            alert.setHeaderText("Check you number at price and quantity!!! ");
            alert.showAndWait();
        }
    }

}
