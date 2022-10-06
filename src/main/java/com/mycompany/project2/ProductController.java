/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.project2;

import com.mycompany.entities.ItemEntity;
import com.mycompany.entities.ProductEntity;
import com.mycompany.entities.RoleEntity;
import com.mycompany.entities.StaffEntity;
import com.mycompany.models.Product;
import com.mycompany.models.Role;
import com.mycompany.models.Staff;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ProductController implements Initializable {

    @FXML
    private Label yourName;

    @FXML
    private Label yourRole;

    @FXML
    private Label yourBirthday;

    @FXML
    private Label yourGender;

    @FXML
    private Label yourAddress;

    @FXML
    private Label yourPhone;

    @FXML
    private Label yourEmail;

    @FXML
    private Button switchToEmployee;

    @FXML
    private VBox chosenProduct;

    @FXML
    private Label title;

    @FXML
    private Label price;

    @FXML
    private ImageView imageView;
            
    @FXML
    private Label quantity;

    @FXML
    private Label seleableNumber;
    
    @FXML
    private Label description;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private Image image;
    
    private ItemEntity itemEntity;

    void setChosenProduct(Product product) {
        title.setText(product.getTitle());
        price.setText("$" + product.getPrice().toString());
        File file = new File(product.getThumbnail());
        if (file.isFile()) {
            image = new Image(file.toURI().toString());
        } else {
            image = new Image(product.getThumbnail(), true);
        }
        imageView.setImage(image);
        quantity.setText(product.getQuantity().toString());
        seleableNumber.setText(product.getSeleableNumber().toString());
        description.setText(product.getDescription());
        
//        chosenProduct.setStyle(string);
    }

    private List<Product> productList = ProductEntity.productList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getFullname();

        getRoleName();

        getBirthday();

        getGender();

        getAddress();

        getPhone();

        getEmail();

        blockManageEmployee();

        int column = 0;
        int row = 1;
        if(productList.size() > 0){
            setChosenProduct(productList.get(0));
            itemEntity = new ItemEntity() {
                @Override
                public void onClick(Product product) {
                    setChosenProduct(product);
                }
            };
        }
        try {
            for (int i = 0; i < productList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(productList.get(i), itemEntity);
//                System.out.println(productList.get(i).getThumbnail());
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child, column, row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                //set grid width
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getFullname() {
        List<Role> roleList = RoleEntity.getRoleList();
        Staff loginId = StaffEntity.findStaffId(Staff.getLoginStaffId());
        yourName.setText("  Fullname: " + loginId.getFullname()); // Find name by id from database to insert into textfield
    }

    private void getRoleName() {
        List<Role> roleList = RoleEntity.getRoleList();
        int roleLogin = Staff.getLoginRoleId();
        for (int i = 0; i < roleList.size(); i++) {
            if (roleList.get(i).getRoleId() == roleLogin) {
                yourRole.setText("Role: " + roleList.get(i).getRoleName().toString());
            }
        }
    }

    private void getBirthday() {
        List<Role> roleList = RoleEntity.getRoleList();
        Staff loginId = StaffEntity.findStaffId(Staff.getLoginStaffId());
        yourBirthday.setText("  Birthday: " + loginId.getBirthday());
    }

    private void getGender() {
        List<Role> roleList = RoleEntity.getRoleList();
        Staff loginId = StaffEntity.findStaffId(Staff.getLoginStaffId());
        yourGender.setText("  Gender: " + loginId.getGender());
    }

    private void getAddress() {
        List<Role> roleList = RoleEntity.getRoleList();
        Staff loginId = StaffEntity.findStaffId(Staff.getLoginStaffId());
        yourAddress.setText("  Address: " + loginId.getAddress());
    }

    private void getPhone() {
        List<Role> roleList = RoleEntity.getRoleList();
        Staff loginId = StaffEntity.findStaffId(Staff.getLoginStaffId());
        yourPhone.setText("  PhoneNumber: 0" + loginId.getPhonenumber());
    }

    private void getEmail() {
        List<Role> roleList = RoleEntity.getRoleList();
        Staff loginId = StaffEntity.findStaffId(Staff.getLoginStaffId());
        yourEmail.setText("  Email: " + loginId.getEmail());
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Staff.setLoginStaffId(null);
        Staff.setEditStaffById(0);

        App.setRoot("login");
    }

    private void blockManageEmployee() {
        if (Staff.getLoginRoleId() != 1) {
            switchToEmployee.setDisable(true);
        }
    }

    @FXML
    private void switchToEmployee() throws IOException {
        App.setRoot("employee");
    }

    @FXML
    private void showIncome() throws IOException {
        App.setRoot("income");
    }

    @FXML
    private void manageCustomer() throws IOException {
        App.setRoot("managecustomer");
    }

    @FXML
    private void switchToCategory() throws IOException {
        App.setRoot("category");
    }

    @FXML
    private void newProduct() throws IOException {
        App.setRoot("editProduct");
    }

    @FXML
    private void btnUpdate() {

    }

    @FXML
    private void switchToOrder() {

    }
}
