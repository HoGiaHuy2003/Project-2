/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.project2;

import com.mycompany.entities.RoleEntity;
import com.mycompany.entities.StaffEntity;
import com.mycompany.models.Role;
import com.mycompany.models.Staff;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
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
        List<Role> roleList = RoleEntity.getRoleList();
        int roleLogin = LoginController.getRoleId();
        Staff loginId = StaffEntity.findId(LoginController.getStaffId());
        yourName.setText("Hello " + loginId.getFullname()); // Find name by id from database to insert into textfield
        for (int i = 0; i < roleList.size(); i++) {
            if (roleList.get(i).getId() == roleLogin) {
                yourRole.setText("Your role is: " + roleList.get(i).getName().toString());
            }
        }
        
        List<Staff> dataList = StaffEntity.list();
        
        TreeItem<Staff> item = new TreeItem<Staff>(dataList.get(0));
        for (int i = 0; i < dataList.size(); i++){
            role.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>(dataList.get(i).getRoleName()));
            fullname.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>(dataList.get(i).getFullname()));
            birthday.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>(dataList.get(i).getBirthday()));
            gender.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>(dataList.get(i).getGender()));
            address.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>(dataList.get(i).getAddress()));
            phoneNumber.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>(dataList.get(i).getPhoneNumber()));
            email.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>(dataList.get(i).getEmail()));
            dateStarted.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>(dataList.get(i).getCreatedAt()));
            TreeItem<Staff> items = new TreeItem<Staff>(dataList.get(i));
            item.getChildren().add(items);
        }
        treeTableView.setRoot(item);
    }
}
