package com.mycompany.project2;

import com.mycompany.entities.RegisterEntity;
import com.mycompany.entities.RoleEntity;
import com.mycompany.models.Register;
import com.mycompany.models.Role;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.scene.layout.FlowPane;

public class RegisterController {
    
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
    
    @FXML
    private void comboBoxRole(){
//        ComboBox<Role> comboBox = new ComboBox<Role>();
        ComboBox comboBox = new ComboBox();
        
        ObservableList<Role> list = RoleEntity.getRoleList();
        
        comboBox.setItems(list);
        comboBox.getSelectionModel().selectFirst();
    }
    
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
    
}
