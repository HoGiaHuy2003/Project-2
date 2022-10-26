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
public class Staff extends Role {

    private int staffId;
    private String fullname;
    private String birthday;
    private String gender;
    private String address;
    private String phonenumber;
    private String email;
    private String password;
    private String createdat;
    private String updatedat;
    private Float totalIncome;

    public Staff(Float totalIncome) {
        this.totalIncome = totalIncome;
    }

    private static int editStaffById; // Select id of staff who need to be editted

    private static Integer loginRoleId; // Role id of staff who login

    private static Integer loginStaffId; //Id staff who login

    public Staff(String roleName, String fullname, String birthday, String gender, String address, String phonenumber, String email, String createdat) {
        super(roleName);
        this.fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phonenumber = phonenumber;
        this.email = email;
        this.createdat = createdat;
    }
    
    public Staff() {
    }

    public Staff(int staffId, String fullname) {
        this.staffId = staffId;
        this.fullname = fullname;
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

    public Staff(int staffId, int roleId, String fullname, String birthday, String gender, String address, String phonenumber, String email, String password, String createdat, String updatedat) {
        super(roleId);
        this.staffId = staffId;
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

    public Staff(int roleId, String fullname, String birthday, String gender, String address, String phonenumber, String email, String password, String createdat, String updatedat) {
        super(roleId);
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

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Float totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public String getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(String updatedat) {
        this.updatedat = updatedat;
    }

    public static int getEditStaffById() {
        return editStaffById;
    }

    public static void setEditStaffById(int editStaffById) {
        Staff.editStaffById = editStaffById;
    }

    public static Integer getLoginRoleId() {
        return loginRoleId;
    }

    public static void setLoginRoleId(Integer loginRoleId) {
        Staff.loginRoleId = loginRoleId;
    }

    public static Integer getLoginStaffId() {
        return loginStaffId;
    }

    public static void setLoginStaffId(Integer loginStaffId) {
        Staff.loginStaffId = loginStaffId;
    }
    
    
}
