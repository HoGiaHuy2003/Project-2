/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.project2;

import com.mycompany.entities.CustomerEntity;
import com.mycompany.entities.OrderEntity;
import com.mycompany.entities.ProductEntity;
import com.mycompany.entities.RoleEntity;
import com.mycompany.entities.StaffEntity;
import com.mycompany.models.Customer;
import com.mycompany.models.Order;
import com.mycompany.models.Role;
import com.mycompany.models.Staff;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class OrderController implements Initializable {

    @FXML
    private Button switchToProduct;
    @FXML
    private Button switchToEmployee;
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
    private Label customerName;
    @FXML
    private Label customerPhoneNumber;
    @FXML
    private TableView<Order> tableView;
    @FXML
    private TableColumn<Order, String> productName;
    @FXML
    private TableColumn<Order, Integer> numberOfProduct;
    @FXML
    private TableColumn<Order, Float> priceOfProduct;
    @FXML
    private Label totalPrice;
    @FXML
    private Label orderDate;

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

        getCustomerName();

        getCustomerPhoneNumber();

        setValueForTableView();

        setTotalPrice();

        setOrderDate();

        blockManageEmployee();
    }

    @FXML
    private void switchToProduct(ActionEvent event) throws IOException {
        App.setRoot("product");
    }

    @FXML
    private void switchToCategory(ActionEvent event) throws IOException {
        App.setRoot("category");
    }

    @FXML
    private void switchToManageCustomer(ActionEvent event) throws IOException {
        App.setRoot("managecustomer");
    }

    @FXML
    private void switchToEmployee(ActionEvent event) throws IOException {
        App.setRoot("employee");
    }

    @FXML
    private void showIncome() throws IOException {
        App.setRoot("income");
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Staff.setLoginStaffId(null);
        Staff.setEditStaffById(0);
        Customer.setValueOfCustomerId(null);

        App.setRoot("login");
    }

    @FXML
    private void confirmOrder() throws IOException {
        List<Order> orderList = Order.getOrderList();
        for (int i = 0; i < orderList.size(); i++) {
            Order order = new Order(OrderEntity.findOrderId().getOrderId(), orderList.get(i).getProductId(), orderList.get(i).getNumberOfProduct(), orderList.get(i).getPriceOfProduct(), Order.getOrderDate());
            OrderEntity.insertOrder(order);
            ProductEntity.updateQuantityAndSeleableNumber(order);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm buying!!!");
        alert.setHeaderText("Buy successfully!!!");
        alert.setHeaderText("You will be to product after click OK!!!");
        alert.showAndWait();
        Order.setOrderList(new Vector<>());
        App.setRoot("product");
    }

    private void getCustomerName() {
        Customer customer = CustomerEntity.findCustomerId(Customer.getValueOfCustomerId());
        customerName.setText("Customer: " + customer.getFullname());
    }

    private void getCustomerPhoneNumber() {
        Customer customer = CustomerEntity.findCustomerId(Customer.getValueOfCustomerId());
        customerPhoneNumber.setText("Phone Number: 0" + customer.getPhoneNumber());
    }

    private void setValueForTableView() {
        ObservableList<Order> orderList = FXCollections.observableList(Order.getOrderList());
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        numberOfProduct.setCellValueFactory(new PropertyValueFactory<>("numberOfProduct"));
        priceOfProduct.setCellValueFactory(new PropertyValueFactory<>("priceOfProduct"));
        tableView.setItems(orderList);
    }

    private void setTotalPrice() {
        totalPrice.setText("Total price: " + Order.getTotalPriceOfProduct());
    }

    private void setOrderDate() {
        orderDate.setText("Order date: " + Order.getOrderDate());
    }

    private void blockManageEmployee() {
        if (Staff.getLoginRoleId() != 1) {
            switchToEmployee.setDisable(true);
            switchToProduct.setDisable(true);
            orderDetail.setDisable(true);
            if (Customer.getValueOfCustomerId() != null && Staff.getLoginStaffId() != null) {
                switchToProduct.setDisable(false);
            }
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
    private void orderDetail() throws IOException {
        App.setRoot("showorder");
    }

}
