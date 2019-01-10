/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainDashBoard;

import BeansPackage.AppointmentItem;
import DatabaseHelper.DBDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import projectfinalkp.FXMLDocumentController;

/**
 * FXML Controller class
 *
 * @author omkarkamate
 */
public class MainDashBoardController implements Initializable {

    @FXML
    private JFXHamburger myHamburger;
    @FXML
    private JFXDrawer menuDrawer;

    @FXML
    private GridPane appointmentGrid;

    ArrayList<String> timeSlotArrayList = new ArrayList<>();
    ArrayList<String> empNameArrayList = new ArrayList<>();
    @FXML
    private JFXDatePicker dateField;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timeSlotArrayList.add(String.valueOf(0));
        empNameArrayList.add(String.valueOf(0));

        initSlider();
        initGrid();
        showtodaysappointments();
    }

    private void initSlider() {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SlideDrawer.fxml"));
            menuDrawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        HamburgerBackArrowBasicTransition hamburgerSlideCloseTransition = new HamburgerBackArrowBasicTransition(myHamburger);
        hamburgerSlideCloseTransition.setRate(-1);
        myHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            hamburgerSlideCloseTransition.setRate(hamburgerSlideCloseTransition.getRate() * -1);
            hamburgerSlideCloseTransition.play();
            if (menuDrawer.isOpened()) {
                menuDrawer.close();

            } else {
                menuDrawer.open();
            }
        });
    }

    private void initGrid() {
        for (int i = 0; i < DBDAO.getAllEmployees().size(); i++) {
            empNameArrayList.add(DBDAO.getAllEmployees().get(i));
            appointmentGrid.getColumnConstraints().add(new ColumnConstraints(150));
            Label text = new Label(DBDAO.getAllEmployees().get(i));
            text.setFont(new Font(20));
            appointmentGrid.addColumn(i + 1, text);

        }

        int time = 9;
        for (int i = 0; i <= 12; i++) {
            timeSlotArrayList.add(String.valueOf(time));
            appointmentGrid.getRowConstraints().add(new RowConstraints(40));
            Label text = new Label(String.valueOf(time));

            text.setFont(new Font(20));
            appointmentGrid.addRow(i + 1, new Text(String.valueOf(time)));
            if (time >= 12) {
                time = 0;
            }

            time++;
        }

    }

    public void showtodaysappointments() {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        today.format(formatter);
        System.out.println(today.toString());
      
        for (AppointmentItem allAppointment : DBDAO.getAllAppointmentsByDate(today.toString())) {

            for (int i = 0; i < timeSlotArrayList.size(); i++) {
                if (allAppointment.getTimeSlot().equals(timeSlotArrayList.get(i))) {
                    for (int j = 0; j < empNameArrayList.size(); j++) {
                        if (allAppointment.getEmpName().equals(empNameArrayList.get(j))) {
                            appointmentGrid.add(new Label("booked for  " + allAppointment.getClientName()), j, i);
                        }
                    }
                }
            }

        }
    }

    @FXML
    private void appointmentOnDateAction(ActionEvent event) {
        // appointmentGrid.getChildren().clear();

        /* for (AppointmentItem allAppointment : DBDAO.getAllAppointmentsByDate(dateField.getValue().toString())) {

            for (int i = 0; i < timeSlotArrayList.size(); i++) {
                if (allAppointment.getTimeSlot().equals(timeSlotArrayList.get(i))) {
                    for (int j = 0; j < empNameArrayList.size(); j++) {
                        if (allAppointment.getEmpName().equals(empNameArrayList.get(j))) {
                            appointmentGrid.add(new Label("booked for  " + allAppointment.getClientName()), j, i);
                        }
                    }
                }
            }

        }*/
      
    }
}
