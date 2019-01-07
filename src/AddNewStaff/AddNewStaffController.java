/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddNewStaff;

import DatabaseHelper.DBDAO;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author omkarkamate
 */
public class AddNewStaffController implements Initializable {

    @FXML
    private JFXTextField empName;
    @FXML
    private JFXTextField empId;
    @FXML
    private JFXListView<String> empList;
       ObservableList<String> data=FXCollections.observableArrayList();

    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      empList.setItems(DBDAO.getAllEmployees());
    }    

    @FXML
    private void addNewUser(ActionEvent event) {
        
        DBDAO.insertEmployee(Integer.parseInt(empId.getText().trim()), empName.getText().trim());
          empList.setItems(DBDAO.getAllEmployees());
        empList.refresh();
    }
    
}
