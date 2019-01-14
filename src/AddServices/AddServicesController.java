/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddServices;

import DatabaseHelper.DBDAO;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author Omskamate
 */
public class AddServicesController implements Initializable {

    @FXML
    private JFXTextField serviceNameField;
    @FXML
    private JFXListView<String> servicesListView;
    @FXML
    private ContextMenu serviceNamesList;
    @FXML
    private JFXTextField serviceIdField;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        servicesListView.setItems(DBDAO.getAllServices());
       
        
        
          serviceIdField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
         System.out.println(" Text Changed to  " + newValue + ")\n");
          serviceNamesList.getItems().clear();
          serviceNamesList.getItems().addAll(DBDAO.getAllServicesbyName(newValue));
         Bounds boundsInScene = serviceIdField.localToScreen(serviceIdField.getBoundsInLocal());
          serviceNamesList.show(serviceIdField,boundsInScene.getMinX(),boundsInScene.getMaxY());
      });
     
     
      serviceNamesList.setOnAction(e ->serviceIdField.setText(((MenuItem)e.getTarget()).getText()));
    
    }    

    @FXML
    private void addServiceAction(ActionEvent event) {
        DBDAO.insertService(serviceNameField.getText().trim());
          servicesListView.setItems(DBDAO.getAllServices());
        servicesListView.refresh();
    }

    @FXML
    private void deleteServiceAction(ActionEvent event) {
        DBDAO.deleteServicebyName(serviceIdField.getText());
          servicesListView.setItems(DBDAO.getAllServices());
           servicesListView.refresh();
    }
    
}
