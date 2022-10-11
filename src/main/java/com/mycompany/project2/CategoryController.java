/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.project2;

import com.mycompany.entities.CategoryEntity;
import com.mycompany.entities.ProductEntity;
import com.mycompany.entities.RoleEntity;
import com.mycompany.entities.StaffEntity;
import com.mycompany.models.Category;
import com.mycompany.models.Customer;
import com.mycompany.models.Product;
import com.mycompany.models.Role;
import com.mycompany.models.Staff;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CategoryController implements Initializable {

    @FXML
    private TableView<Product> tableview;
    @FXML
    private Button switchToEmployee;
    @FXML
    private TableColumn<Product, Integer> columnID;
    @FXML
    private TableColumn<Product, String> columnTiltle;
    @FXML
    private TableColumn<Product, Integer> columnQuantity;
    @FXML
    private TableColumn<Product, String> columnDescription;

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
    private ComboBox cbCategory;

    @FXML
    private Button switchToProduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setUpComboBox();

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

    @FXML
    private void getselect(ActionEvent event) throws IOException {
        String select = cbCategory.getSelectionModel().getSelectedItem().toString();
//          if ("food".equals(select)) {
//            setValueForTableView();
//        }
        List<Category> categoryList = CategoryEntity.getCategoryList();
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getCategoryName().equals(select)) {
                setValueForTableViewShowProductByCategory();
                break;
            }
        }
    }

    private void setUpComboBox() {
        ObservableList<Category> list = (ObservableList<Category>) CategoryEntity.getCategoryList();
        for (int i = 0; i < list.size(); i++) {
            cbCategory.getItems().addAll(list.get(i).getCategoryName());
            cbCategory.setValue(list.get(i).getCategoryName());
            cbCategory.getSelectionModel().select(null);
        }

    }

    private void setValueForTableViewShowProductByCategory() {
        ObservableList<Product> productList = (ObservableList<Product>) ProductEntity.findProductByCategory(cbCategory.getSelectionModel().getSelectedItem().toString());
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTiltle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableview.setItems(productList);
    }

    private void setValueForTableView() {
        ObservableList<Product> productList = (ObservableList<Product>) ProductEntity.productList();
        
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTiltle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableview.setItems(productList);
    }

    @FXML
    private void manageCustomer(ActionEvent event) throws IOException {
        App.setRoot("managecustomer");
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
    private void switchToOrder() throws IOException {
        App.setRoot("order");
    }

    @FXML
    private void showIncome() throws IOException {
        App.setRoot("income");
    }

    @FXML
    private void orderDetail() throws IOException {
        App.setRoot("showorder");
    }

}
