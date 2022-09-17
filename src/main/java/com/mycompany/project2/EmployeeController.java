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
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;

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
    private TableView<Staff> tableview;

    @FXML
    private TableColumn<Staff, String> role;

    @FXML
    private TableColumn<Staff, String> fullname;

    @FXML
    private TableColumn<Staff, String> birthday;

    @FXML
    private TableColumn<Staff, String> gender;

    @FXML
    private TableColumn<Staff, String> address;

    @FXML
    private TableColumn<Staff, String> phoneNumber;

    @FXML
    private TableColumn<Staff, String> email;

    @FXML
    private TableColumn<Staff, String> dateStarted;

//    @FXML
//    private TreeTableView<Staff> treeTableView;
//
//    @FXML
//    private TreeTableColumn<Staff, String> role;
//
//    @FXML
//    private TreeTableColumn<Staff, String> fullname;
//
//    @FXML
//    private TreeTableColumn<Staff, String> birthday;
//
//    @FXML
//    private TreeTableColumn<Staff, String> gender;
//
//    @FXML
//    private TreeTableColumn<Staff, String> address;
//
//    @FXML
//    private TreeTableColumn<Staff, String> phoneNumber;
//
//    @FXML
//    private TreeTableColumn<Staff, String> email;
//
//    @FXML
//    private TreeTableColumn<Staff, String> dateStarted;

    private TreeItem<Staff> item;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        getFullname();

        getRoleName();

        setValueForTableView();

    }

    private void getFullname() {
        List<Role> roleList = RoleEntity.getRoleList();
        Staff loginId = StaffEntity.findId(LoginController.getStaffId());
        yourName.setText("Hello " + loginId.getFullname()); // Find name by id from database to insert into textfield
    }

    private void getRoleName() {
        List<Role> roleList = RoleEntity.getRoleList();
        int roleLogin = LoginController.getRoleId();
        for (int i = 0; i < roleList.size(); i++) {
            if (roleList.get(i).getId() == roleLogin) {
                yourRole.setText("Your role is: " + roleList.get(i).getName().toString());
            }
        }
    }

    private void setValueForTableView() {
        ObservableList<Staff> employerList = StaffEntity.employerList();

        ObservableList<Staff> employeeList = StaffEntity.employeeList();

        role.setCellValueFactory(new PropertyValueFactory<>("rolename"));
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateStarted.setCellValueFactory(new PropertyValueFactory<>("createdat"));

        tableview.setItems(employerList);

        tableview.getItems().addAll(employeeList);
    }

//    private void setValueForTreeTableView() {
//        role.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("rolename"));
//        fullname.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("fullname"));
//        birthday.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("birthday"));
//        gender.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("gender"));
//        address.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("address"));
//        phoneNumber.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("phonenumber"));
//        email.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("email"));
//        dateStarted.setCellValueFactory(new TreeItemPropertyValueFactory<Staff, String>("createdat"));
//        treeTableView.getColumns().addAll(role, fullname, birthday, gender, address, phoneNumber, email, dateStarted);
//        item = new TreeItem<Staff>(dataList.get(0));
//        for (int i = 0; i < dataList.size(); i++) {
//            TreeItem<Staff> items = new TreeItem<Staff>(dataList.get(i));
////
//            item.getChildren().addAll(items);
//            item = new TreeItem<Staff>(dataList.get(i));
//
//        }
//            treeTableView.setRoot(item);
//        }
//    }
}
