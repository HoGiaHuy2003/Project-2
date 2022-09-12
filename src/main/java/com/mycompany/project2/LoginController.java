package com.mycompany.project2;

import com.mycompany.entities.RegisterEntity;
import com.mycompany.models.Register;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.event.ActionEvent;
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
        String username = txtUsername.getText().toString();
        String password = md5Password(txtPassword.getText().toString());
        Register register = new Register(username, password);
        if(RegisterEntity.find(register).getEmail().equals(username) || RegisterEntity.find(register).getPhoneNumber().equals(username) && RegisterEntity.find(register).getPassword().equals(password)){
            switchToRegister();
        }
    }
}
