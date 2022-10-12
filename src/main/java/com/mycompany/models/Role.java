/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Admin
 */
public class Role {

    private int roleId;
    private String roleName;
    private String password;

    public Role() {
    }

    public Role(int roleId) {
        this.roleId = roleId;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }
    
    public Role(int roleId, String roleName, String password) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//    
//    public abstract String getFullname();
//    public abstract void setFullname(String fullname);
//    public abstract 
}
