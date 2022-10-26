/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.project2;

import com.mycompany.entities.IncomeEntity;
import com.mycompany.entities.StaffEntity;
import com.mycompany.models.Income;
import com.mycompany.models.Staff;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class EditIncomeController implements Initializable {

    @FXML
    private ComboBox staffName;
    @FXML
    private TextField txtSalary;
    @FXML
    private TextField txtOvertimeWages;
    @FXML
    private TextField txtRetroactivePay;
    @FXML
    private TextField txtCommissions;
    @FXML
    private TextField txtBonus;
    @FXML
    private TextField txtTips;
    @FXML
    private TextField txtPenalty;
    @FXML
    private Label back;

    private ObservableList<Income> findStaff = IncomeEntity.findStaffId(Staff.getEditStaffById());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setUpComboBox();
        System.out.println(Staff.getEditStaffById());

        if (Staff.getEditStaffById() != 0) {
            staffName.setValue(findStaff.get(0).getFullname());
            txtSalary.setText(findStaff.get(0).getSalaryWages().toString());
            txtOvertimeWages.setText(findStaff.get(0).getOvertimeWages().toString());
            txtRetroactivePay.setText(findStaff.get(0).getRetroactivePay().toString());
            txtCommissions.setText(findStaff.get(0).getCommissions().toString());
            txtBonus.setText(findStaff.get(0).getCommissions().toString());
            txtTips.setText(findStaff.get(0).getTips().toString());
            txtPenalty.setText(findStaff.get(0).getPenalty().toString());
        }
    }

    private void setUpComboBox() {
        List<Staff> staffList = StaffEntity.list();
        for (int i = 0; i < staffList.size(); i++) {
            staffName.getItems().addAll(staffList.get(i).getFullname());
            staffName.setValue(staffList.get(i).getFullname());
            staffName.getSelectionModel().select(null);
        }
    }

    @FXML
    private void save(ActionEvent event) throws IOException {
        int staffId = 0;
        while (true) {
            try {
                float salary = Float.parseFloat(txtSalary.getText().toString());
                float overtimeWages = Float.parseFloat(txtOvertimeWages.getText().toString());
                float retroactivePay = Float.parseFloat(txtRetroactivePay.getText().toString());
                float commissions = Float.parseFloat(txtCommissions.getText().toString());
                float bonus = Float.parseFloat(txtBonus.getText().toString());
                float tips = Float.parseFloat(txtTips.getText().toString());
                float penalty = Float.parseFloat(txtPenalty.getText().toString());
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String createdAt = dateFormat.format(date);
                String updatedAt = createdAt;

                List<Staff> staffList = StaffEntity.list();

                List<Income> incomeListInDataBase = IncomeEntity.incomeListInDataBase();
                for (int j = 0; j < incomeListInDataBase.size(); j++) {
                    if (Staff.getEditStaffById() == incomeListInDataBase.get(j).getStaffId()) {
                        for (int i = 0; i < staffList.size(); i++) {
                            if (staffList.get(i).getFullname().equals(staffName.getValue().toString())) {
                                staffId = staffList.get(i).getStaffId();
                                Income income = new Income(staffId, staffName.getValue().toString(), salary, overtimeWages, retroactivePay, commissions, bonus, tips, penalty, createdAt, updatedAt);
                                IncomeEntity.update(income);
                                App.setRoot("income");
                                return;
                            }
                        }
                    }
                }

                for (int i = 0; i < staffList.size(); i++) {
                    if (staffList.get(i).getFullname().equals(staffName.getValue().toString())) {
                        staffId = staffList.get(i).getStaffId();
                        Income income = new Income(staffId, staffName.getValue().toString(), salary, overtimeWages, retroactivePay, commissions, bonus, tips, penalty, createdAt, updatedAt);
                        IncomeEntity.insert(income);
                        App.setRoot("income");
                        break;
                    }
                }
                break;
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error edit income");
                alert.setHeaderText("Can not edit income!!!");
                alert.setContentText("Income must be a float number!!!");
            }
        }
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("income");
    }
}
