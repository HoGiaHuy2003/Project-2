package com.mycompany.project2;

import com.mycompany.entities.RegisterEntity;
import com.mycompany.entities.RoleEntity;
import com.mycompany.models.Register;
import com.mycompany.models.Role;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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
    private VBox pane_register;

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
    private TextArea txtAddress;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtRolePassword;

    private ToggleGroup group = new ToggleGroup(); //for button
    
    private RadioButton button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Role> list = RoleEntity.getRoleList();
        for (int i = 0; i < list.size(); i++) {
            cbRole.getItems().addAll(list.get(i).getName());
            cbRole.setValue(list.get(i).getName());
            cbRole.getSelectionModel().select(null);
        }
        radioButton();
//        comboBoxRole();
    }

//    @FXML
//    private void comboBoxRole() {
//        ObservableList<Role> list = RoleEntity.getRoleList();
//
//        for (int i = 0; i < list.size(); i++) {
//            cbRole.getItems().addAll(list.get(i).getName());
//            cbRole.getSelectionModel().select(null);
//        }
//    }
    @FXML
    private void radioButton() {
        btnMale = new RadioButton("Male");
        btnFemale = new RadioButton("Female");

        btnMale.setToggleGroup(group);

        btnFemale.setToggleGroup(group);
//        if (group.getSelectedToggle() == btnMale) {
//            button = btnMale;
//        } else if (group.getSelectedToggle() == btnFemale) {
//            button = btnFemale;
//        }
    }

    private String getValueOfRadioButton() {
//        radioButton();     
        button = btnMale;
        if(group.getSelectedToggle() != null){
            button = (RadioButton) group.getSelectedToggle();
        }
        return button.getText();
    }

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
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
        int role_id = 0;
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

        for (int i = 0; i < RoleEntity.getRoleList().size(); i++) {
            if (RoleEntity.getRoleList().get(i).getName().equals(cbRole.getValue())) {
                role_id = RoleEntity.getRoleList().get(i).getId();
                Register register = new Register(role_id, fullname, birthday, gender, address, phoneNumber, email, password, createdAt, updatedAt);
                if (RoleEntity.getRoleList().get(i).getPassword().equals(rollPassword)) {
                    RegisterEntity.insert(register);
                    switchToLogin();
                    break;
                }
            }
        }
    }
}