/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientProfile;

import BeansPackage.TransactionItem;
import DatabaseHelper.DBDAO;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Omskamate
 */
public class ClientProfileController implements Initializable {

    @FXML
    private Label idField;
    @FXML
    private Label NameField;
    @FXML
    private Label MobnoFIeld;
    @FXML
    private Label DOBFIeld;
    @FXML
    private Label emailFIeld;
    @FXML
    private Label genderField;
    @FXML
    private TableView<TransactionItem> transactionTable;
    @FXML
    private Label amountBalanceField;
    @FXML
    private Label totalbusinessField;

    double totalBusinessAmount = 0;
    @FXML
    private Label lastTransactionDateField;

    String latestTransactionDate;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<TransactionItem, Integer> transactionId = new TableColumn("transactionId");
        transactionId.setCellValueFactory(new PropertyValueFactory<>("transactionId"));

        TableColumn<TransactionItem, String> empName = new TableColumn("empName");
        empName.setCellValueFactory(new PropertyValueFactory<>("empName"));

        TableColumn<TransactionItem, String> serviceName = new TableColumn("serviceName");
        serviceName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));

        TableColumn<TransactionItem, String> clientNameColumn = new TableColumn("clientName");
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));

        TableColumn<TransactionItem, String> clientMobileNumber = new TableColumn("clientMobileNumber");
        clientMobileNumber.setCellValueFactory(new PropertyValueFactory<>("clientMobileNumber"));

        TableColumn<TransactionItem, Integer> clientId = new TableColumn("clientId");
        clientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));

        TableColumn<TransactionItem, String> transactionDate = new TableColumn("transactionDate");
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));

        TableColumn<TransactionItem, Float> amount = new TableColumn("amount");
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        transactionTable.getColumns().addAll(transactionId, empName, serviceName, clientNameColumn, clientMobileNumber, clientId, transactionDate, amount);
        transactionTable.setItems(DBDAO.getAllTransactionByClientId(DBDAO.clientId));
        for (int i = 0; i < DBDAO.getAllTransactionByClientId(DBDAO.clientId).size(); i++) {
            totalBusinessAmount += DBDAO.getAllTransactionByClientId(DBDAO.clientId).get(i).getAmount();
            latestTransactionDate = DBDAO.getAllTransactionByClientId(DBDAO.clientId).get(i).getTransactionDate();
        }
        System.out.println("latestTransactionId :" + latestTransactionDate);

        transactionTable.refresh();
        setClientInfo();

    }

    public void setClientInfo() {
        try {
            ResultSet rs = DBDAO.getClientObjectByphNo(DBDAO.phno);
            NameField.setText("Name : " + rs.getString("clientName"));
            idField.setText("ID : " + rs.getInt("clientId"));
            MobnoFIeld.setText("Mobile Number  : " + rs.getString("clientMobileNumber"));
            DOBFIeld.setText("DOB  : " + rs.getString("clientDOB"));
            emailFIeld.setText("Email Id : " + rs.getString("clientEmail"));
            genderField.setText("Gender : " + rs.getString("clientGender"));
           
            amountBalanceField.setText("Amount Balance : " + rs.getDouble("amountBalance"));
            
            totalbusinessField.setText("Total Business  : " + totalBusinessAmount);
            lastTransactionDateField.setText("Last Transaction Date : "+latestTransactionDate);
        } catch (SQLException ex) {
            Logger.getLogger(ClientProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
