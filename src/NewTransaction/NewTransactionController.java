/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewTransaction;

import BeansPackage.TransactionItem;
import DatabaseHelper.DBDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author omkarkamate
 */
public class NewTransactionController implements Initializable {

    @FXML
    private JFXComboBox<String> empNameList;
    @FXML
    private TableView<TransactionItem> transactionTable;
    
      ObservableList<TransactionItem> data=FXCollections.observableArrayList();
    @FXML
    private JFXTextField serviceNameField;
    @FXML
    private JFXTextField clientNameField;
    @FXML
    private JFXDatePicker dateField;
    @FXML
    private JFXTextField amountField;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empNameList.setItems(DBDAO.getAllEmployees());
        
         TableColumn<TransactionItem,Integer> transactionId=new TableColumn("transactionId");
              transactionId.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
              
              TableColumn<TransactionItem,String> empName=new TableColumn("empName");
              empName.setCellValueFactory(new PropertyValueFactory<>("empName"));
              
              TableColumn<TransactionItem,String>serviceName=new TableColumn("serviceName");
              serviceName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
              
              TableColumn<TransactionItem,String>clientName=new TableColumn("clientName");
              clientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
              
              TableColumn<TransactionItem,String>transactionDate=new TableColumn("transactionDate");
              transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
              
              TableColumn<TransactionItem,Float>amount=new TableColumn("amount");
              amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
              
             
              transactionTable.getColumns().addAll(transactionId,empName,serviceName,clientName,transactionDate,amount);
              transactionTable.setItems(DBDAO.getAllTransactions());
              transactionTable.refresh();
              
                }    

    @FXML
    private void addTransaction(ActionEvent event) {
        
        DBDAO.insertNewTransaction(empNameList.getValue(), serviceNameField.getText().trim(), clientNameField.getText().trim(), dateField.getValue().toString(), Float.parseFloat(amountField.getText().trim()));
         transactionTable.setItems(DBDAO.getAllTransactions());
          transactionTable.refresh();
              
    }
    
    
}
