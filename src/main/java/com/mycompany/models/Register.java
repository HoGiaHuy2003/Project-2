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

/**
 *
 * @author Admin
 */
public class Register {
    
    private int roleId;
    private String fullname;
    private String birthday;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private String rollPassword;
    private String createdAt;
    private String updatedAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Register() {
    }

    public Register(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Register(int roleId, String fullname, String birthday, String gender, String address, String phoneNumber, String email, String password, String createdAt, String updatedAt) throws NoSuchAlgorithmException {
        this.roleId = roleId;
        this.fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
//        StringBuffer sb = new StringBuffer();
//        for (byte b : hashInBytes) {
//            sb.append(String.format("%02x", b));
//        }
//        password = sb.toString();
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
