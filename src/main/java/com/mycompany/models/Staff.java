/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Admin
 */
public class Staff {

    private int id;
    private int roleId;
    private String rolename;
    private String fullname;
    private String birthday;
    private String gender;
    private String address;
    private String phonenumber;
    private String email;
    private String password;
    private String rollPassword;
    private String createdat;
    private String updatedat;

    public Staff(String rolename, String fullname, String birthday, String gender, String address, String phonenumber, String email, String createdat) {
        this.rolename = rolename;
        this.fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phonenumber = phonenumber;
        this.email = email;
        this.createdat = createdat;
    }

    public Staff(int id, int roleId, String fullname, String birthday, String gender, String address, String phonenumber, String email, String password, String rollPassword, String createdat, String updatedat) {
        this.id = id;
        this.roleId = roleId;
        this.fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.rollPassword = rollPassword;
        this.createdat = createdat;
        this.updatedat = updatedat;
    }

    public Staff() {
    }

    public Staff(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Staff(String phonenumber, String email, String password) {
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
    }

    public Staff(int id, int roleId, String fullname, String birthday, String gender, String address, String phonenumber, String email, String password, String createdat, String updatedat) {
        this.id = id;
        this.roleId = roleId;
        this.fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.createdat = createdat;
        this.updatedat = updatedat;
    }

    public Staff(int roleId, String fullname, String birthday, String gender, String address, String phonenumber, String email, String password, String createdat, String updatedat) throws NoSuchAlgorithmException {
        this.roleId = roleId;
        this.fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.createdat = createdat;
        this.updatedat = updatedat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdAt) {
        this.createdat = createdAt;
    }

    public String getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(String updatedat) {
        this.updatedat = updatedat;
    }

    public String getRollPassword() throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        byte[] hashInBytes = md.digest(rollPassword.getBytes(StandardCharsets.UTF_8));
//        StringBuffer sb = new StringBuffer();
//        for (byte b : hashInBytes) {
//            sb.append(String.format("%02x", b));
//        }
//        this.rollPassword = sb.toString();
        return rollPassword;
    }

    public void setRollPassword(String rollPassword) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        byte[] hashInBytes = md.digest(rollPassword.getBytes(StandardCharsets.UTF_8));
//        StringBuffer sb = new StringBuffer();
//        for (byte b : hashInBytes) {
//            sb.append(String.format("%02x", b));
//        }
        this.rollPassword = rollPassword;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
//        StringBuffer sb = new StringBuffer();
//        for (byte b : hashInBytes) {
//            sb.append(String.format("%02x", b));
//        }
//        this.password = sb.toString();
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
//        StringBuffer sb = new StringBuffer();
//        for (byte b : hashInBytes) {
//            sb.append(String.format("%02x", b));
//        }
//        password = sb.toString();
        this.password = password;
    }
}
