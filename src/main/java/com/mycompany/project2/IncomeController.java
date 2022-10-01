/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.project2;

import com.mycompany.entities.IncomeEntity;
import com.mycompany.entities.RoleEntity;
import com.mycompany.entities.StaffEntity;
import com.mycompany.models.Income;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class IncomeController implements Initializable {

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
    private TableView<Income> tableview;
    @FXML
    private TableColumn<Income, String> fullname;
    @FXML
    private TableColumn<Income, Float> salary;
    @FXML
    private TableColumn<Income, Float> overtime;
    @FXML
    private TableColumn<Income, Float> retroactivePay;
    @FXML
    private TableColumn<Income, Float> commissions;
    @FXML
    private TableColumn<Income, Float> bonus;
    @FXML
    private TableColumn<Income, Float> tips;
    @FXML
    private TableColumn<Income, Float> penalty;
    @FXML
    private TableColumn<Income, String> createdAt;
    @FXML
    private TableColumn<Income, String> updatedAt;

    @FXML
    private Button btnUpdate;
    
    @FXML
    private Button showAll;
    
    @FXML
    private Button switchToEmployee;

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
//
        blockManageEmployee();
        
        disableChangingInformation();
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
        ObservableList<Income> findIncomeOfthisStaff = IncomeEntity.findStaffId(Staff.getEditStaffById());
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salaryWages"));
        overtime.setCellValueFactory(new PropertyValueFactory<>("overtimeWages"));
        retroactivePay.setCellValueFactory(new PropertyValueFactory<>("retroactivePay"));
        commissions.setCellValueFactory(new PropertyValueFactory<>("commissions"));
        bonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        tips.setCellValueFactory(new PropertyValueFactory<>("tips"));
        penalty.setCellValueFactory(new PropertyValueFactory<>("penalty"));
        createdAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        updatedAt.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        tableview.setItems(findIncomeOfthisStaff);
        if (Staff.getEditStaffById() == 0) {
            ObservableList<Income> incomeList = IncomeEntity.incomeListShowAll();
            tableview.setItems(incomeList);
        }
    }

    @FXML
    private void showAll() {
        ObservableList<Income> incomeList = IncomeEntity.incomeListShowAll();
        tableview.setItems(incomeList);
    }

    @FXML
    private void btnUpdate(ActionEvent event) throws IOException {
        ObservableList<Income> incomeList = IncomeEntity.incomeListShowAll();

//        ObservableList<Income> incomeListInDataBase = IncomeEntity.incomeListInDataBase();
//
//        for (int i = 0; i < incomeListInDataBase.size(); i++) {
//            if (tableview.getSelectionModel().getSelectedItem().getStaffId() == incomeListInDataBase.get(i).getStaffId()) {
//                Staff.setEditStaffById(incomeList.get(i).getStaffId());
////                System.out.println(Staff.getEditStaffById());
//                App.setRoot("editIncome");
//                break;
//            }
//        }

        for (int i = 0; i < incomeList.size(); i++) {
            if (tableview.getSelectionModel().getSelectedItem().getStaffId() == incomeList.get(i).getStaffId()) {
                Staff.setEditStaffById(incomeList.get(i).getStaffId());
//                System.out.println(Staff.getEditStaffById());
                App.setRoot("editIncome");
                break;
            }
        }
    }
    
    private void blockManageEmployee() {
        if (Staff.getLoginRoleId() != 1) {
            switchToEmployee.setDisable(true);
        }
    }
    
    private void disableChangingInformation() {
        if (Staff.getLoginRoleId() == 2) {
            btnUpdate.setDisable(true);
            showAll.setDisable(true);
        }
    }

//    @FXML
//    private void btnDelete(ActionEvent event) {
//    }

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
    private void switchToEmployee() throws IOException {
        App.setRoot("employee");
    }

    @FXML
    private void manageCustomer() throws IOException {
        App.setRoot("managecustomer");
    }

}
