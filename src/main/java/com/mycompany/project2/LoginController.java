package com.mycompany.project2;

import java.io.IOException;
import javafx.fxml.FXML;
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
    
}