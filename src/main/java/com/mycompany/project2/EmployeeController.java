/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.project2;

import com.mycompany.entities.RoleEntity;
import com.mycompany.entities.StaffEntity;
import com.mycompany.models.Staff;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class EmployeeController implements Initializable {

    @FXML
    private Label yourName;

    @FXML
    private Label yourRole;
    
    @FXML
    private TreeTableView<Staff> treeTableView;
    
    @FXML
    private TreeTableColumn<Staff, String> role;
    
    @FXML 
    private TreeTableColumn<Staff, String> fullname;
    
    @FXML 
    private TreeTableColumn<Staff, String> birthday;
    
    @FXML
    private TreeTableColumn<Staff, String> gender;
    
    @FXML
    private TreeTableColumn<Staff, String> address;
    
    @FXML
    private TreeTableColumn<Staff, String> phoneNumber;
    
    @FXML
    private TreeTableColumn<Staff, String> email;
    
    @FXML
    private TreeTableColumn<Staff, String> dateStarted;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        role.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("roleName"));
        fullname.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("fullname"));
        birthday.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("birthday"));
        gender.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("gender"));
        address.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("address"));
        phoneNumber.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("phoneNumber"));
        email.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("email"));
        dateStarted.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("createdAt"));
        yourName.setText("Hello " + StaffEntity.findId(LoginController.getStaffId()).getFullname()); // Find name by id from database to insert into textfield
        for (int i = 0; i < RoleEntity.getRoleList().size(); i++) {
            if (RoleEntity.getRoleList().get(i).getId() == LoginController.getRoleId()) {
                yourRole.setText("Your role is: " + RoleEntity.getRoleList().get(i).getName().toString());
            }
        }
        TreeItem<Staff> item = new TreeItem<Staff>(StaffEntity.list().get(0));
        for(int i = 0; i < StaffEntity.list().size(); i++){
            TreeItem<Staff> items = new TreeItem<Staff>(StaffEntity.list().get(i));
            item.getChildren().add(items);
        }
        treeTableView.setRoot(item);
    }
}
