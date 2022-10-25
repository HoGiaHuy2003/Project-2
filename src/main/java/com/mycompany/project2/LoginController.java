package com.mycompany.project2;

import com.mycompany.entities.StaffEntity;
import com.mycompany.models.Staff;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("register");
    }

    @FXML
    private void switchToManageCustomer() throws IOException {
        App.setRoot("managecustomer");
    }

    private String md5Password(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuffer sb = new StringBuffer();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        password = sb.toString();
        return password;
    }

    @FXML
    private void login(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        Staff staff = new Staff();
        staff.setEmail(txtUsername.getText().toString());
        staff.setPhonenumber(txtUsername.getText().toString());
        staff.setPassword(md5Password(txtPassword.getText().toString()));
//        if (StaffEntity.login(staff).getEmail().equals(staff.getEmail()) || StaffEntity.login(staff).getPhonenumber().equals(staff.getPhonenumber()) && StaffEntity.login(staff).getPassword().equals(staff.getPassword())) {
//            Staff.setLoginStaffId(StaffEntity.login(staff).getStaffId());
//            Staff.setLoginRoleId(StaffEntity.login(staff).getRoleId());
//            Staff.setEditStaffById(Staff.getLoginStaffId());
//            switchToManageCustomer();
//        }
        Staff login = StaffEntity.login(staff);
        if (login != null) {
            Staff.setLoginStaffId(StaffEntity.login(staff).getStaffId());
            Staff.setLoginRoleId(StaffEntity.login(staff).getRoleId());
            Staff.setEditStaffById(Staff.getLoginStaffId());
            switchToManageCustomer();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setHeaderText("Cannot login!!!");
            alert.setContentText("Username and password are incorrect, please check again!!!");
            alert.showAndWait();
        }
    }
}
