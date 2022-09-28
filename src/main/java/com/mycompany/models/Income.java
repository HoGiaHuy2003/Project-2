/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

/**
 *
 * @author Admin
 */
public class Income extends Staff {

    private Float salaryWages;
    private Float overtimeWages;
    private Float retroactivePay;
    private Float commissions;
    private Float bonus;
    private Float tips;
    private Float penalty;
    private String createdAt;
    private String updatedAt;

    public Income() {
    }

    public Income(int staffId, String fullname, Float salaryWages, Float overtimeWages, Float retroactivePay, Float commissions, Float bonus, Float tips, Float penalty, String createdAt, String updatedAt) {
        super(staffId, fullname);
        this.salaryWages = salaryWages;
        this.overtimeWages = overtimeWages;
        this.retroactivePay = retroactivePay;
        this.commissions = commissions;
        this.bonus = bonus;
        this.tips = tips;
        this.penalty = penalty;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Float getSalaryWages() {
        return salaryWages;
    }

    public void setSalaryWages(Float salaryWages) {
        this.salaryWages = salaryWages;
    }

    public Float getOvertimeWages() {
        return overtimeWages;
    }

    public void setOvertimeWages(Float overtimeWages) {
        this.overtimeWages = overtimeWages;
    }

    public Float getRetroactivePay() {
        return retroactivePay;
    }

    public void setRetroactivePay(Float retroactivePay) {
        this.retroactivePay = retroactivePay;
    }

    public Float getCommissions() {
        return commissions;
    }

    public void setCommissions(Float commissions) {
        this.commissions = commissions;
    }

    public Float getBonus() {
        return bonus;
    }

    public void setBonus(Float bonus) {
        this.bonus = bonus;
    }

    public Float getTips() {
        return tips;
    }

    public void setTips(Float tips) {
        this.tips = tips;
    }

    public Float getPenalty() {
        return penalty;
    }

    public void setPenalty(Float penalty) {
        this.penalty = penalty;
    }

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
}
