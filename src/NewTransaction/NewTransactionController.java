/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewTransaction;

import AddClients.AddClientsController;
import BeansPackage.TransactionItem;
import DatabaseHelper.DBDAO;
import animatefx.animation.FadeInDownBig;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    ObservableList<TransactionItem> data = FXCollections.observableArrayList();
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
    private ContextMenu serviceNamesList;

    @FXML
    private JFXTextField idField;
    
    ResultSet resultSet;

    /**
     * Initializes the controller class.
     */
    public void inititlizeTable() {

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
        transactionTable.setItems(DBDAO.getAllTransactions());
        transactionTable.refresh();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inititlizeTable();
        empNameList.setItems(DBDAO.getAllEmployees());

        serviceNameField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            System.out.println(" Text Changed to  " + newValue + ")\n");
            serviceNamesList.getItems().clear();
            serviceNamesList.getItems().addAll(DBDAO.getAllServicesbyName(newValue));
            Bounds boundsInScene = serviceNameField.localToScreen(serviceNameField.getBoundsInLocal());
            serviceNamesList.show(serviceNameField, boundsInScene.getMinX(), boundsInScene.getMaxY());
        });

        serviceNamesList.setOnAction(e -> serviceNameField.setText(((MenuItem) e.getTarget()).getText()));

        clientNameField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            clientNamesList.getItems().clear();
            clientNamesList.getItems().addAll(DBDAO.searchClientByName(newValue));
            Bounds boundsInScene = clientNameField.localToScreen(clientNameField.getBoundsInLocal());
            clientNamesList.show(clientNameField, boundsInScene.getMinX(), boundsInScene.getMaxY());
        });

        mobileNumberField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            mobnumberList.getItems().clear();
            mobnumberList.getItems().addAll(DBDAO.searchClientByphNo(newValue));
            Bounds boundsInScene = mobileNumberField.localToScreen(mobileNumberField.getBoundsInLocal());
            mobnumberList.show(mobileNumberField, boundsInScene.getMinX(), boundsInScene.getMaxY());
        });

        clientNamesList.setOnAction(e
                -> clientNameField.setText(((MenuItem) e.getTarget()).getText()));

        mobnumberList.setOnAction(e
                -> {
            try {
                String phno = ((MenuItem) e.getTarget()).getText();
                mobileNumberField.setText(phno);
                resultSet = DBDAO.getClientObjectByphNo(phno);

                idField.setText(String.valueOf(resultSet.getLong("clientId")));
                clientNameField.setText(resultSet.getString("clientName"));
                //amountField.setText(String.valueOf(resultSet.getDouble("amountBalance")));
                transactionTable.setItems(DBDAO.getAllTransactionByClientId(resultSet.getInt("clientId")));
            } catch (Exception ex) {
                Logger.getLogger(NewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        transactionTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                try {
                    DBDAO.clientId = transactionTable.getSelectionModel().getSelectedItem().getClientId();
                    DBDAO.phno = transactionTable.getSelectionModel().getSelectedItem().getClientMobileNumber();

                    Stage closeStage = (Stage) transactionTable.getScene().getWindow();
                    closeStage.close();
                    AnchorPane root = FXMLLoader.load(getClass().getResource("/ClientProfile/ClientProfile.fxml"));

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                    new FadeInDownBig(root).play();
                } catch (IOException ex) {
                    Logger.getLogger(AddClientsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Delete Transaction");
                alert.setContentText("Are you sure you want to delete this transaction?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    DBDAO.deleteTransaction(transactionTable.getSelectionModel().getSelectedItem().getTransactionId());
                    transactionTable.setItems(DBDAO.getAllTransactions());
                    transactionTable.refresh();
                }
            }

        });

    }

    @FXML
    private void addTransaction(ActionEvent event) {

        try {
            DBDAO.insertNewTransaction(empNameList.getValue(), serviceNameField.getText().trim(), clientNameField.getText(), mobileNumberField.getText().trim(), Long.parseLong(idField.getText().trim()), dateField.getValue().toString(), Double.parseDouble(amountField.getText().trim()));
            DBDAO.updateAmountBalanceofCLient(resultSet.getLong("clientId"),(resultSet.getDouble("amountBalance")- Double.parseDouble(amountField.getText().trim())));
            transactionTable.setItems(DBDAO.getAllTransactions());
            transactionTable.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(NewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
