/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import com.mycompany.models.Income;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class IncomeEntity extends BaseEntity {

    public static void insert(Income income) {
        open();

        try {
            String sql = "INSERT INTO Income(staff_id, salary_wages, overtime_wages, retroactive_pay, commissions, bonus, tips, penalty, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, income.getStaffId());
            statement.setFloat(2, income.getSalaryWages());
            statement.setFloat(3, income.getOvertimeWages());
            statement.setFloat(4, income.getRetroactivePay());
            statement.setFloat(5, income.getCommissions());
            statement.setFloat(6, income.getBonus());
            statement.setFloat(7, income.getTips());
            statement.setFloat(8, income.getPenalty());
            statement.setString(9, income.getCreatedAt());
            statement.setString(10, income.getUpdatedAt());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }

    public static void update(Income income) {
        open();

        try {
            String sql = "UPDATE income SET salary_wages = ?, overtime_wages = ?, retroactive_pay = ?, commissions = ?, bonus = ?, tips = ?, penalty = ?, updated_at = ? WHERE staff_id = ?";
            statement = conn.prepareStatement(sql);

            statement.setFloat(1, income.getSalaryWages());
            statement.setFloat(2, income.getOvertimeWages());
            statement.setFloat(3, income.getRetroactivePay());
            statement.setFloat(4, income.getCommissions());
            statement.setFloat(5, income.getBonus());
            statement.setFloat(6, income.getTips());
            statement.setFloat(7, income.getPenalty());
            statement.setString(8, income.getUpdatedAt());
            statement.setInt(9, income.getStaffId());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }

    public static void delete(int staffId) {
        open();

        try {
            String sql = "DELETE FROM income WHERE staff_id = ?";

            statement = conn.prepareStatement(sql);

            statement.setInt(1, staffId);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(IncomeEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }

    public static ObservableList<Income> incomeListShowAll() {
        List<Income> incomeList = new Vector<>();
        open();

        try {
            String sql = "SELECT staff.id, staff.fullname, income.salary_wages, income.overtime_wages, income.retroactive_pay, income.commissions, income.bonus, income.tips, income.penalty, income.created_at, income.updated_at FROM staff LEFT JOIN income ON staff.id = income.staff_id";
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Income income = new Income(resultSet.getInt("id"), resultSet.getString("fullname"), resultSet.getFloat("salary_wages"), resultSet.getFloat("overtime_wages"), resultSet.getFloat("retroactive_pay"), resultSet.getFloat("commissions"), resultSet.getFloat("bonus"), resultSet.getFloat("tips"), resultSet.getFloat("penalty"), resultSet.getString("created_at"), resultSet.getString("updated_at"));
                incomeList.add(income);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

        ObservableList<Income> dataList = FXCollections.observableList(incomeList);

        return dataList;
    }

    public static ObservableList<Income> incomeListInDataBase() {
        List<Income> incomeList = new Vector<>();
        open();

        try {
            String sql = "SELECT staff.id, staff.fullname, income.salary_wages, income.overtime_wages, income.retroactive_pay, income.commissions, income.bonus, income.tips, income.penalty, income.created_at, income.updated_at FROM staff RIGHT JOIN income ON staff.id = income.staff_id";
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Income income = new Income(resultSet.getInt("id"), resultSet.getString("fullname"), resultSet.getFloat("salary_wages"), resultSet.getFloat("overtime_wages"), resultSet.getFloat("retroactive_pay"), resultSet.getFloat("commissions"), resultSet.getFloat("bonus"), resultSet.getFloat("tips"), resultSet.getFloat("penalty"), resultSet.getString("created_at"), resultSet.getString("updated_at"));
                incomeList.add(income);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

        ObservableList<Income> dataList = FXCollections.observableList(incomeList);

        return dataList;
    }

    public static ObservableList<Income> findStaffId(int staffId) {
        List<Income> incomeList = new Vector<>();
        open();

        try {
            String sql = "SELECT staff.id, staff.fullname, income.salary_wages, income.overtime_wages, income.retroactive_pay, income.commissions, income.bonus, income.tips, income.penalty, income.created_at, income.updated_at FROM staff LEFT JOIN income ON staff.id = income.staff_id WHERE staff.id = ?";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, staffId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Income income = new Income(resultSet.getInt("id"), resultSet.getString("fullname"), resultSet.getFloat("salary_wages"), resultSet.getFloat("overtime_wages"), resultSet.getFloat("retroactive_pay"), resultSet.getFloat("commissions"), resultSet.getFloat("bonus"), resultSet.getFloat("tips"), resultSet.getFloat("penalty"), resultSet.getString("created_at"), resultSet.getString("updated_at"));
                incomeList.add(income);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IncomeEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

        ObservableList<Income> dataList = FXCollections.observableList(incomeList);

        return dataList;
    }
}
