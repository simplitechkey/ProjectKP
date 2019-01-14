/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainDashBoard;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author omkarkamate
 */
public class SlideDrawerController implements Initializable {


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addOrRemoveAction(ActionEvent event) {
        try {
            AnchorPane root= FXMLLoader.load(getClass().getResource("/AddNewStaff/AddNewStaff.fxml"));
             
               Scene scene = new Scene(root);
               Stage stage=new Stage();
               stage.setScene(scene);
               
               stage.show();
               
        } catch (IOException ex) {
            Logger.getLogger(SlideDrawerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addNewTransaction(ActionEvent event) {
        
        try {
            AnchorPane root= FXMLLoader.load(getClass().getResource("/NewTransaction/NewTransaction.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SlideDrawerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addNewClientAction(ActionEvent event) {
        
           try {
            AnchorPane root= FXMLLoader.load(getClass().getResource("/AddClients/AddClients.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SlideDrawerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void bookAppointmentAction(ActionEvent event) {
        try {
            AnchorPane root= FXMLLoader.load(getClass().getResource("/AppointmentBooking/AppointmentBooking.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SlideDrawerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void addServiceAction(ActionEvent event) {
        
         try {
            AnchorPane root= FXMLLoader.load(getClass().getResource("/AddServices/AddServices.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SlideDrawerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void servicesDueAction(ActionEvent event) {
         try {
            AnchorPane root= FXMLLoader.load(getClass().getResource("/ServicesDue/ServicesDue.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SlideDrawerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void salesReportAction(ActionEvent event) {
    }

    @FXML
    private void inventoryAction(ActionEvent event) {
    }

    @FXML
    private void helpAction(ActionEvent event) {
    }
    
}
