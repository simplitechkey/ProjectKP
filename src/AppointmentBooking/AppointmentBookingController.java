/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppointmentBooking;

import BeansPackage.AppointmentItem;
import BeansPackage.ClientItem;
import DatabaseHelper.DBDAO;
import NewTransaction.NewTransactionController;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

/**
 * FXML Controller class
 *
 * @author omkarkamate
 */
public class AppointmentBookingController implements Initializable {
    
    @FXML
    private ContextMenu empNameList;
    @FXML
    private ContextMenu clientMobileNumberList;
    @FXML
    private JFXTextField clientNameField;
    @FXML
    private JFXTextField timeSlotField;
    @FXML
    private ContextMenu timeslotList;
    @FXML
    private JFXDatePicker dateField;
    @FXML
    private JFXTextField statusField;
    @FXML
    private JFXTextField empNameField;
    @FXML
    private JFXTextField clientMobileNumberField;
    @FXML
    private TableView<AppointmentItem> appointmentTable;
    @FXML
    private ContextMenu statusList;
    
    ArrayList<String> timeSlotArrayList = new ArrayList<>(Arrays.asList("0", "9am to 10am", "10am to 11am", "11am to 12pm", "12pm to 1pm", "1pm to 2pm", "2pm to 3pm", "3pm to 4pm", "4pm to 5pm", "5pm to 6pm", "6pm to 7pm", "7pm to 8pm", "8pm to 9pm"));
    
    public void initContextMenus() {
        for (int i = 0; i < DBDAO.getAllEmployees().size(); i++) {
            empNameList.getItems().add(new MenuItem(DBDAO.getAllEmployees().get(i)));
        }
        statusList.getItems().add(new MenuItem("booked"));
        statusList.getItems().add(new MenuItem("completed"));
        
        statusField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            
            Bounds boundsInScene = statusField.localToScreen(statusField.getBoundsInLocal());
            statusList.show(statusField, boundsInScene.getMinX(), boundsInScene.getMaxY());
        });
        
        empNameField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            
            Bounds boundsInScene = empNameField.localToScreen(empNameField.getBoundsInLocal());
            empNameList.show(empNameField, boundsInScene.getMinX(), boundsInScene.getMaxY());
        });
        
        for (int i = 1; i < timeSlotArrayList.size(); i++) {
            
            timeslotList.getItems().add(new MenuItem(String.valueOf(timeSlotArrayList.get(i))));
            
        }
        
        timeSlotField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            
            Bounds boundsInScene = timeSlotField.localToScreen(timeSlotField.getBoundsInLocal());
            timeslotList.show(timeSlotField, boundsInScene.getMinX(), boundsInScene.getMaxY());
        });
        
        clientMobileNumberField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            
            clientMobileNumberList.getItems().clear();
            clientMobileNumberList.getItems().addAll(DBDAO.searchClientByphNo(newValue));
            Bounds boundsInScene = clientMobileNumberField.localToScreen(clientMobileNumberField.getBoundsInLocal());
            clientMobileNumberList.show(clientMobileNumberField, boundsInScene.getMinX(), boundsInScene.getMaxY());
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initAppointmentTable();
        initContextMenus();
        initContextOnclick();
        appointmentTable.setOnMouseClicked( event -> {
   if( event.getClickCount() == 2 ) {
      System.out.println( appointmentTable.getSelectionModel().getSelectedItem().getAppointmentId());
   }if(event.getButton()==MouseButton.SECONDARY){
        System.out.println( appointmentTable.getSelectionModel().getSelectedItem().getAppointmentId());
   }
        
        });
    }
    
    @FXML
    private void bookAppointmentAction(ActionEvent event) {
        
        DBDAO.insertNewAppointment(dateField.getValue().toString(), timeSlotField.getText(), clientNameField.getText(), clientMobileNumberField.getText(), empNameField.getText(), statusField.getText());
        appointmentTable.setItems(DBDAO.getAllAppointments());
        appointmentTable.refresh();
    }
    
    private void initAppointmentTable() {
        TableColumn<AppointmentItem, Integer> appointmentId = new TableColumn("appointmentId");
        appointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        
        TableColumn<AppointmentItem, String> date = new TableColumn("date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        TableColumn<AppointmentItem, String> timeSlot = new TableColumn("timeSlot");
        timeSlot.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        
        TableColumn<AppointmentItem, String> clientName = new TableColumn("clientName");
        clientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        
        TableColumn<AppointmentItem, String> clientMobileNumber = new TableColumn("clientMobileNumber");
        clientMobileNumber.setCellValueFactory(new PropertyValueFactory<>("clientMobileNumber"));
        
        TableColumn<AppointmentItem, String> empName = new TableColumn("empName");
        empName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        
        TableColumn<AppointmentItem, String> appointmentStatus = new TableColumn("appointmentStatus");
        appointmentStatus.setCellValueFactory(new PropertyValueFactory<>("appointmentStatus"));
        
        appointmentTable.getColumns().addAll(appointmentId, date, timeSlot, clientName, clientMobileNumber, empName, appointmentStatus);
        appointmentTable.setItems(DBDAO.getAllAppointments());
        appointmentTable.refresh();
    }
    
    private void initContextOnclick() {
        clientMobileNumberList.setOnAction(e
                -> {
            String phno = ((MenuItem) e.getTarget()).getText();
            clientMobileNumberField.setText(phno);
            ResultSet rs = DBDAO.getClientObjectByphNo(phno);
            
            try {
                clientNameField.setText(rs.getString("clientName"));
            } catch (SQLException ex) {
                Logger.getLogger(AppointmentBookingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        timeslotList.setOnAction(e
                -> {
            try {
                String timeslot = ((MenuItem) e.getTarget()).getText();
                timeSlotField.setText(timeslot);
            } catch (Exception ex) {
                Logger.getLogger(NewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        statusList.setOnAction(e
                -> {
            try {
                String status = ((MenuItem) e.getTarget()).getText();
                statusField.setText(status);
            } catch (Exception ex) {
                Logger.getLogger(NewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        empNameList.setOnAction(e
                -> {
            try {
                String status = ((MenuItem) e.getTarget()).getText();
                empNameField.setText(status);
            } catch (Exception ex) {
                Logger.getLogger(NewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
}
