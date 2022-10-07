/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.project2;

import com.mycompany.entities.IncomeEntity;
import com.mycompany.entities.RoleEntity;
import com.mycompany.entities.StaffEntity;
import com.mycompany.models.Customer;
import com.mycompany.models.Income;
import com.mycompany.models.Role;
import com.mycompany.models.Staff;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private TableView<Staff> tableview;

    @FXML
    private TableColumn<Role, String> role;

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

    @FXML
    private TableColumn<Staff, Float> totalIncome;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button switchToEmployee;

    @FXML
    private Button switchToProduct;

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

        getBirthday();

        getGender();

        getAddress();

        getPhone();

        getEmail();

        setValueForTableView();

        blockManageEmployee();

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

    private void setValueForTableView() {
        ObservableList<Staff> employerList = StaffEntity.employerList();

        ObservableList<Staff> employeeList = StaffEntity.employeeList();

//        ObservableList<Staff> total = StaffEntity.totalIncome();
        role.setCellValueFactory(new PropertyValueFactory<>("roleName"));
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateStarted.setCellValueFactory(new PropertyValueFactory<>("createdat"));
//        totalIncome.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Staff, Float>, ObservableValue<Float>>) total);

        tableview.setItems(employerList);

        tableview.getItems().addAll(employeeList);
    }

    private void blockManageEmployee() {
        if (Staff.getLoginRoleId() != 1) {
            switchToEmployee.setDisable(true);
        }
        switchToProduct.setDisable(true);
        if (Customer.getValueOfCustomerId() != null && Staff.getLoginStaffId() != null) {
            switchToProduct.setDisable(false);
        }
    }

//    @FXML
//    private void setNullForSelectionModel(){
//        if(tableview.getSelectionModel().getSelectedItem() != null){
//            tableview.setSelectionModel(null);
//        }
//    }
    @FXML
    private void btnUpdate(ActionEvent event) throws IOException {
        ObservableList<Staff> employerList = StaffEntity.employerList();

        ObservableList<Staff> employeeList = StaffEntity.employeeList();

        ObservableList<Staff> staffList = StaffEntity.list();
        for (int i = 0; i < staffList.size(); i++) {
            if (tableview.getSelectionModel().getSelectedItem().getStaffId() == staffList.get(i).getStaffId()) {
                Staff.setEditStaffById(staffList.get(i).getStaffId());
//                System.out.println(Staff.getEditStaffById());
                App.setRoot("register");
                break;
            }
        }
    }

    @FXML
    private void btnDelete(ActionEvent event) throws IOException {
        ObservableList<Staff> staffList = StaffEntity.list();
        for (int i = 0; i < staffList.size(); i++) {
            if (tableview.getSelectionModel().getSelectedItem().getStaffId() == staffList.get(i).getStaffId()) {
                Staff.setEditStaffById(staffList.get(i).getStaffId());
                break;
            }
        }
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete this staff: ");
        alert.setHeaderText("Are you sure want to delete this staff?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {

        } else if (option.get() == ButtonType.OK) {
            IncomeEntity.delete(Staff.getEditStaffById());
            StaffEntity.delete(Staff.getEditStaffById());
            if (Staff.getEditStaffById() == Staff.getLoginStaffId()) {
                Staff.setLoginStaffId(null);
                Staff.setEditStaffById(0);
            }
            tableview.getItems().remove(tableview.getSelectionModel().getSelectedItem());
            if (Staff.getLoginStaffId() == null) {
                App.setRoot("login");
            }
        } else if (option.get() == ButtonType.OK) {

        }
    }

    @FXML
    private void showIncome(ActionEvent event) throws IOException {
        ObservableList<Staff> staffList = StaffEntity.list();
        for (int i = 0; i < staffList.size(); i++) {
            if (tableview.getSelectionModel().getSelectedItem() == null) {
                Staff.setEditStaffById(0);
                App.setRoot("income");
            }
            if (tableview.getSelectionModel().getSelectedItem().getStaffId() == staffList.get(i).getStaffId()) {
                Staff.setEditStaffById(staffList.get(i).getStaffId());
//                System.out.println(Staff.getEditStaffById());
                App.setRoot("income");
                break;
            }
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Staff.setLoginStaffId(null);
        Staff.setEditStaffById(0);

        App.setRoot("login");
    }

    @FXML
    private void switchToProduct() throws IOException {
        App.setRoot("product");
    }

    @FXML
    private void switchToCategory() throws IOException {
        App.setRoot("category");
    }

    @FXML
    private void manageCustomer() throws IOException {
        App.setRoot("managecustomer");
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
