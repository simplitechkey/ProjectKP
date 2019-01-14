/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesDue;

import BeansPackage.ClientItem;
import DatabaseHelper.DBDAO;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author Omskamate
 */
public class ServicesDueController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static long Max_Limit_Services_Due = 30;
    ArrayList<String> clientNames=new ArrayList<>();
    long diff;
    String lastDate="";
    @FXML
    private JFXListView<String> servicesDueList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (ClientItem item : DBDAO.getAllClients()) {
            int listsize=DBDAO.getAllTransactionByClientId(item.getClientId()).size();
            if(listsize>0)
            {
            for (int i = 0; i < DBDAO.getAllTransactionByClientId(item.getClientId()).size(); i++) {
                lastDate= DBDAO.getAllTransactionByClientId(item.getClientId()).get(i).getTransactionDate();
            }
            if(isServicesDue(lastDate)){
           
             servicesDueList.getItems().add(item.getClientName()+"   "+item.getClientMobileNumber());
            }
            }  
        }
        
    }

    public boolean isServicesDue(String lastServiceDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate issuedDate = LocalDate.parse(lastServiceDate, formatter);
        LocalDate today = LocalDate.now();
        diff = DAYS.between(issuedDate, today);

        return diff > Max_Limit_Services_Due;
    }
}
