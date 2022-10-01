/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.project2;

import com.mycompany.entities.CustomerEntity;
import com.mycompany.entities.RoleEntity;
import com.mycompany.entities.StaffEntity;
import com.mycompany.models.Customer;
import com.mycompany.models.Role;
import com.mycompany.models.Staff;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ManagecustomerController implements Initializable {

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
    private TableView<Customer> tableview;

    @FXML
    private TableColumn<Customer, String> fullname;

    @FXML
    private TableColumn<Customer, String> birthday;

    @FXML
    private TableColumn<Customer, String> gender;

    @FXML
    private TableColumn<Customer, String> address;

    @FXML
    private TableColumn<Customer, String> phoneNumber;

    @FXML
    private TableColumn<Customer, String> email;

    @FXML
    private TableColumn<Customer, String> dateStarted;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button switchToEmployee;

    @FXML
    private void btnUpdate() throws IOException {
        ObservableList<Customer> customerList = CustomerEntity.customerList();
        for (int i = 0; i < customerList.size(); i++) {
            if (tableview.getSelectionModel().getSelectedItem().getId() == customerList.get(i).getId()) {
                Customer.setValueOfCustomerId(customerList.get(i).getId());
                App.setRoot("customer");
                break;
            }
        }
    }

    @FXML
    private void btnDelete() {
        ObservableList<Customer> customerList = CustomerEntity.customerList();
        for (int i = 0; i < customerList.size(); i++) {
            if (tableview.getSelectionModel().getSelectedItem().getId() == customerList.get(i).getId()) {
                Customer.setValueOfCustomerId(customerList.get(i).getId());
                break;
            }
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete this customer: ");
        alert.setHeaderText("Are you sure want to delete this customer?");

        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            CustomerEntity.delete(Customer.getValueOfCustomerId());
            tableview.getItems().remove(tableview.getSelectionModel().getSelectedItem());
        }
    }

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
        ObservableList<Customer> customerList = CustomerEntity.customerList();
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateStarted.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        tableview.setItems(customerList);
    }

    private void blockManageEmployee() {
        if (Staff.getLoginRoleId() != 1) {
            switchToEmployee.setDisable(true);
        }
    }

    @FXML
    private void newCustomer() throws IOException {
        App.setRoot("customer");
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
    private void switchToEmployee() throws IOException {
        App.setRoot("employee");
    }
    
    @FXML
    private void switchToCategory() throws IOException {
        App.setRoot("category");
    }

    @FXML
    private void chooseCustomer() throws IOException {
        ObservableList<Customer> customerList = CustomerEntity.customerList();
        for (int i = 0; i < customerList.size(); i++) {
            if (tableview.getSelectionModel().getSelectedItem().getId() == customerList.get(i).getId()) {
                Customer.setValueOfCustomerId(customerList.get(i).getId());
                break;
            }
        }
        App.setRoot("product");
    }

    @FXML
    private void btnUpdateAccount(ActionEvent event) throws IOException {
        Staff.setEditStaffById(Staff.getLoginStaffId());
        App.setRoot("register");
    }

    @FXML
    private void btnDeleteAccount(ActionEvent event) throws IOException {
        Staff.setEditStaffById(Staff.getLoginStaffId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete this staff: ");
        alert.setHeaderText("Are you sure want to delete this staff?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {

        } else if (option.get() == ButtonType.OK) {
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
    private void showIncome() throws IOException {
        App.setRoot("income");
    }
}
