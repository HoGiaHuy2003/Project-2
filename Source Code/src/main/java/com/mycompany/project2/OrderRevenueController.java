/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.project2;

import com.mycompany.entities.ItemEntity;
import com.mycompany.entities.OrderEntity;
import com.mycompany.entities.RoleEntity;
import com.mycompany.entities.StaffEntity;
import com.mycompany.models.Customer;
import com.mycompany.models.Order;
import com.mycompany.models.Product;
import com.mycompany.models.Role;
import com.mycompany.models.Staff;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class OrderRevenueController implements Initializable {

    @FXML
    private Button switchToProduct;
    @FXML
    private Button switchToEmployee;
    @FXML
    private Button switchToOrder;
    @FXML
    private Button orderDetail;
    @FXML
    private Label yourRole;
    @FXML
    private Label yourName;
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
    private Label totalRevenue;
    @FXML
    private TableView<Order> tableView;
    @FXML
    private TableColumn<Order, Integer> columnId;
    @FXML
    private TableColumn<Order, String> columnStaffName;
    @FXML
    private TableColumn<Order, String> columnCustomerName;
    @FXML
    private TableColumn<Order, String> columnOrderDate;
    @FXML
    private TableColumn<Order, Float> columnTotalPrice;

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

        setValueForTableView();
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
        Customer.setValueOfCustomerId(null);

        App.setRoot("login");
    }

    private void blockManageEmployee() {
        if (Staff.getLoginRoleId() != 1) {
            switchToEmployee.setDisable(true);
            orderDetail.setDisable(true);
        }
        switchToProduct.setDisable(true);
        switchToOrder.setDisable(true);
        if (Customer.getValueOfCustomerId() != null && Staff.getLoginStaffId() != null) {
            switchToProduct.setDisable(false);
            switchToOrder.setDisable(false);
        }
    }

    private void setValueForTableView() {
        ObservableList<Order> orderRevenue = FXCollections.observableList(OrderEntity.revenueList());

        columnId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        columnStaffName.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        columnCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        columnOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderdate"));
        columnTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        tableView.setItems(orderRevenue);

        totalRevenue.setText("Total Revenue: " + OrderEntity.totalRevenue());
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
    private void switchToProduct() throws IOException {
        App.setRoot("product");
    }

    @FXML
    private void orderDetail() throws IOException {
        App.setRoot("showorder");
    }
}
