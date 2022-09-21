/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.project2;

import com.mycompany.entities.CustomerEntity;
import com.mycompany.models.Customer;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CustomerController implements Initializable {

    @FXML
    private Label title;

    @FXML
    private TextField txtFullname;

    @FXML
    private DatePicker txtBirthday;

    @FXML
    private RadioButton btnMale;

    @FXML
    private RadioButton btnFemale;

    private RadioButton button;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label switchToManageCustomer;

    @FXML
    private void getBtnMale() {
        button = btnMale;
    }

    @FXML
    private void getBtnFemale() {
        button = btnFemale;
    }

    private String getValueOfRadioButton() {
        return button.getText();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        if(Customer.getValueOfCustomerId() != null){
//            title.setText("Edit customer");
//            switchToProduct.setText("Cancel");
//            txtFullname.setText(string);
//        }
        button = btnMale;
        txtBirthday.setValue(LocalDate.now());

        if (Customer.getValueOfCustomerId() != null) {
            title.setText("Update customer");
            txtFullname.setText(CustomerEntity.findCustomerId(Customer.getValueOfCustomerId()).getFullname());
            txtBirthday.setValue(LocalDate.parse(CustomerEntity.findCustomerId(Customer.getValueOfCustomerId()).getBirthday()));
            txtAddress.setText(CustomerEntity.findCustomerId(Customer.getValueOfCustomerId()).getAddress());
            txtPhoneNumber.setText(CustomerEntity.findCustomerId(Customer.getValueOfCustomerId()).getPhoneNumber());
            txtEmail.setText(CustomerEntity.findCustomerId(Customer.getValueOfCustomerId()).getEmail());
            switchToManageCustomer.setText("Cancel");
        }
    }

    @FXML
    private void save(ActionEvent event) throws IOException {
        String fullname = txtFullname.getText().toString();
        String birthday = txtBirthday.getValue().toString();
        String gender = getValueOfRadioButton();
        String address = txtAddress.getText().toString();
        String phoneNumber = txtPhoneNumber.getText().toString();
        String email = txtEmail.getText().toString();
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createdAt = dateFormat.format(date);
        String updatedAt = createdAt;

        if (Customer.getValueOfCustomerId() == null) {
            Customer customer = new Customer(fullname, birthday, gender, address, phoneNumber, email, createdAt, updatedAt);
            CustomerEntity.insert(customer);
            App.setRoot("managecustomer");
        } else {
            Customer customer = new Customer(fullname, birthday, gender, address, phoneNumber, email, createdAt, updatedAt);
            customer.setId(Customer.getValueOfCustomerId());
            CustomerEntity.update(customer);
            App.setRoot("managecustomer");
        }
    }

    @FXML
    private void switchToManageCustomer() throws IOException {
        App.setRoot("managecustomer");
    }

}
