/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddClients;

import BeansPackage.ClientItem;
import BeansPackage.TransactionItem;
import DatabaseHelper.DBDAO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Omskamate
 */
public class AddClientsController implements Initializable {

    @FXML
    private JFXTextField clientNameField;
    @FXML
    private JFXTextField clientMobNoField;
    @FXML
    private JFXTextField clientEmailField;
    @FXML
    private JFXDatePicker clientDOBField;
    @FXML
    private JFXComboBox<String> clientGenderField;
    @FXML
    private JFXComboBox<String> locationField;
    @FXML
    private TableView<ClientItem> clientTableView;
    @FXML
    private JFXTextField amountPending;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientGenderField.getItems().add("male");
        clientGenderField.getItems().add("females");
        locationField.getItems().add("NIBM");
          locationField.getItems().add("KP");
        
         TableColumn<ClientItem,Integer> clientId=new TableColumn("clientId");
              clientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
              
              TableColumn<ClientItem,String> clientName=new TableColumn("clientName");
              clientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
              
              TableColumn<ClientItem,String>clientMobileNumber=new TableColumn("clientMobileNumber");
              clientMobileNumber.setCellValueFactory(new PropertyValueFactory<>("clientMobileNumber"));
              
              TableColumn<ClientItem,String>clientEmail=new TableColumn("clientEmail");
              clientEmail.setCellValueFactory(new PropertyValueFactory<>("clientEmail"));
              
              TableColumn<ClientItem,String>clientDOB=new TableColumn("clientDOB");
              clientDOB.setCellValueFactory(new PropertyValueFactory<>("clientDOB"));
              
              TableColumn<ClientItem,String>clientGender=new TableColumn("clientGender");
              clientGender.setCellValueFactory(new PropertyValueFactory<>("clientGender"));
              
               TableColumn<ClientItem,String>location=new TableColumn("location");
              location.setCellValueFactory(new PropertyValueFactory<>("location"));
              
               TableColumn<ClientItem,Double>amountBalance=new TableColumn("amountBalance");
              amountBalance.setCellValueFactory(new PropertyValueFactory<>("amountBalance"));
             
             
              clientTableView.getColumns().addAll(clientId,clientName,clientMobileNumber,clientEmail,clientDOB,clientGender,location,amountBalance);
              clientTableView.setItems(DBDAO.getAllClients());
              clientTableView.refresh();
    }    

    @FXML
    private void addClientAction(ActionEvent event) {
        DBDAO.insertNewClient(clientNameField.getText().trim(), clientMobNoField.getText().trim(), clientEmailField.getText().trim(), clientDOBField.getValue().toString(), clientGenderField.getValue(), locationField.getValue(),Double.parseDouble(amountPending.getText()));
          clientTableView.setItems(DBDAO.getAllClients());
          clientTableView.refresh();
    }
    
}
