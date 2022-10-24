package com.mycompany.project2;

import com.mycompany.entities.StaffEntity;
import com.mycompany.entities.RoleEntity;
import com.mycompany.models.Staff;
import com.mycompany.models.Role;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class RegisterController implements Initializable {

    @FXML
    private Label title;

    @FXML
    private ComboBox cbRole;

    @FXML
    private TextField txtFullname;

    @FXML
    private DatePicker txtBirthday;

    @FXML
    private RadioButton btnMale;

    @FXML
    private RadioButton btnFemale;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtRolePassword;

    @FXML
    private Label forgotPassword;

    @FXML
    private Label cancelOrSwitchToLogin;

    @FXML
    private Button btnSave;

    private RadioButton button;

    private Staff getEditStaff = StaffEntity.findStaffId(Staff.getEditStaffById());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpComboBox();
        txtBirthday.setValue(LocalDate.now());
        button = btnMale;

        if (Staff.getEditStaffById() != 0) {
            title.setText("                 Edit account for user");
            cancelOrSwitchToLogin.setText("Cancel");
            forgotPassword.setText(null);
            cbRole.setValue(getEditStaff.getRoleName());
            txtFullname.setText(getEditStaff.getFullname());
            txtBirthday.setValue(LocalDate.parse(getEditStaff.getBirthday()));
            txtAddress.setText(getEditStaff.getAddress());
            txtPhoneNumber.setText(getEditStaff.getPhonenumber());
            txtEmail.setText(getEditStaff.getEmail());
            btnSave.setText("Save");
        }
    }

    private void setUpComboBox() {
        ObservableList<Role> list = RoleEntity.getRoleList();
        for (int i = 0; i < list.size(); i++) {
            cbRole.getItems().addAll(list.get(i).getRoleName());
            cbRole.setValue(list.get(i).getRoleName());
            cbRole.getSelectionModel().select(null);
        }
    }

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

    @FXML
    private void switchToLoginOrManageCustomer() throws IOException {
        if (Staff.getEditStaffById() == 0) {
            App.setRoot("login");
        } else {
            App.setRoot("managecustomer");
        }
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
    private void register(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        String fullname = txtFullname.getText().toString();
        String birthday = txtBirthday.getValue().toString();
        String gender = getValueOfRadioButton();
        String address = txtAddress.getText().toString();
        String phoneNumber = txtPhoneNumber.getText().toString();
        String email = txtEmail.getText().toString();
        String password = md5Password(txtPassword.getText().toString());
        String rollPassword = md5Password(txtRolePassword.getText().toString());
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createdAt = dateFormat.format(date);
        String updatedAt = createdAt;
        List<Role> getRoleList = RoleEntity.getRoleList();
        if (Staff.getEditStaffById() == 0) {
            for (int i = 0; i < getRoleList.size(); i++) {
                if (getRoleList.get(i).getRoleName().equals(cbRole.getValue())) {
                    int role_id = getRoleList.get(i).getRoleId();
                    Staff register = new Staff(role_id, fullname, birthday, gender, address, phoneNumber, email, password, createdAt, updatedAt);
                    if (getRoleList.get(i).getPassword().equals(rollPassword)) {
                        StaffEntity.insert(register);
                        switchToLoginOrManageCustomer();
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < getRoleList.size(); i++) {
                if (getRoleList.get(i).getRoleName().equals(cbRole.getValue())) {
                    int role_id = getRoleList.get(i).getRoleId();
                    Staff update = new Staff(role_id, fullname, birthday, gender, address, phoneNumber, email, password, createdAt, updatedAt);
                    update.setStaffId(Staff.getEditStaffById());
                    if (getRoleList.get(i).getPassword().equals(rollPassword)) {
                        StaffEntity.update(update);
                        Staff.setEditStaffById(Staff.getLoginStaffId());
                        switchToLoginOrManageCustomer();
                        break;
                    }
                }
            }
        }
    }
}
