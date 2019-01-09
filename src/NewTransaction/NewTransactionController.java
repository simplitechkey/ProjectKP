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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
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
    @FXML
    private ContextMenu clientNamesList;
    @FXML
    private JFXTextField mobileNumberField;
    @FXML
    private ContextMenu mobnumberList;
    @FXML
    private JFXTextField idField;
   
   
  

    /**
     * Initializes the controller class.
     */
    
    public void inititlizeTable(){
        
           TableColumn<TransactionItem,Integer> transactionId=new TableColumn("transactionId");
              transactionId.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
              
              TableColumn<TransactionItem,String> empName=new TableColumn("empName");
              empName.setCellValueFactory(new PropertyValueFactory<>("empName"));
              
              TableColumn<TransactionItem,String>serviceName=new TableColumn("serviceName");
              serviceName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
              
              TableColumn<TransactionItem,String>clientNameColumn=new TableColumn("clientName");
              clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
              
               TableColumn<TransactionItem,String>clientMobileNumber=new TableColumn("clientMobileNumber");
              clientMobileNumber.setCellValueFactory(new PropertyValueFactory<>("clientMobileNumber"));
              
               TableColumn<TransactionItem,Integer>clientId=new TableColumn("clientId");
              clientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
              
              
              TableColumn<TransactionItem,String>transactionDate=new TableColumn("transactionDate");
              transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
              
              TableColumn<TransactionItem,Float>amount=new TableColumn("amount");
              amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
              
             
              transactionTable.getColumns().addAll(transactionId,empName,serviceName,clientNameColumn,clientMobileNumber,clientId,transactionDate,amount);
              transactionTable.setItems(DBDAO.getAllTransactions());
              transactionTable.refresh();
              
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        inititlizeTable();
        empNameList.setItems(DBDAO.getAllEmployees());
        
      
              clientNameField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        
          clientNamesList.getItems().clear();
          clientNamesList.getItems().addAll(DBDAO.searchClientByName(newValue));
         Bounds boundsInScene = clientNameField.localToScreen(clientNameField.getBoundsInLocal());
          clientNamesList.show(clientNameField,boundsInScene.getMinX(),boundsInScene.getMaxY());
      });
              
            mobileNumberField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        
          mobnumberList.getItems().clear();
          mobnumberList.getItems().addAll(DBDAO.searchClientByphNo(newValue));
         Bounds boundsInScene = mobileNumberField.localToScreen(mobileNumberField.getBoundsInLocal());
          mobnumberList.show(mobileNumberField,boundsInScene.getMinX(),boundsInScene.getMaxY());
      });  
     
     
      clientNamesList.setOnAction(e ->
              
              clientNameField.setText(((MenuItem)e.getTarget()).getText()));
     
      
      
           mobnumberList.setOnAction(e ->
          
             {
            try {
                String phno=((MenuItem)e.getTarget()).getText();
                mobileNumberField.setText(phno);
                ResultSet rs=DBDAO.getClientObjectByphNo(phno);
              
                idField.setText(String.valueOf(rs.getInt("clientId")));
                clientNameField.setText(rs.getString("clientName"));
                  amountField.setText(String.valueOf(rs.getDouble("amountBalance")));
                 transactionTable.setItems(DBDAO.getAllClientTransactionsById(rs.getInt("clientId")));
            } catch (SQLException ex) {
                Logger.getLogger(NewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         
    }    

    @FXML
    private void addTransaction(ActionEvent event) {
        
        DBDAO.insertNewTransaction(empNameList.getValue(), serviceNameField.getText().trim(), clientNameField.getText(),mobileNumberField.getText().trim(),Integer.parseInt(idField.getText().trim()), dateField.getValue().toString(), Float.parseFloat(amountField.getText().trim()));
         transactionTable.setItems(DBDAO.getAllTransactions());
          transactionTable.refresh();
              
    }
    
    
}
