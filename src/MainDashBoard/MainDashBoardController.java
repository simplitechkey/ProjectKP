/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainDashBoard;

import BeansPackage.AppointmentItem;
import DatabaseHelper.DBDAO;
import animatefx.animation.FadeInDownBig;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.sun.prism.paint.Color;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
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

    ArrayList<String> timeSlotArrayList = new ArrayList<>(Arrays.asList("0", "9am to 10am", "10am to 11am", "11am to 12pm", "12pm to 1pm", "1pm to 2pm", "2pm to 3pm", "3pm to 4pm", "4pm to 5pm", "5pm to 6pm", "6pm to 7pm", "7pm to 8pm", "8pm to 9pm"));
    ArrayList<String> empNameArrayList = new ArrayList<>();
    @FXML
    private JFXDatePicker dateField;
    @FXML
    private Label headingId;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        empNameArrayList.add(String.valueOf(0));

        initSlider();
        initGrid();
        showAppointmentsByDates(DBDAO.getDateforAppointments());
        headingId.setText("Below are Appointments for the  Date  :  " + DBDAO.getDateforAppointments());
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
            Label text = new Label("   "+DBDAO.getAllEmployees().get(i));
            text.setStyle("-fx-text-fill: white;");

            text.setFont(new Font(20));
            appointmentGrid.addColumn(i + 1, text);

        }

        for (int i = 1; i < timeSlotArrayList.size(); i++) {

            appointmentGrid.getRowConstraints().add(new RowConstraints(40));
            Label text = new Label(String.valueOf(timeSlotArrayList.get(i)));

            text.setFont(new Font(15));
            text.setStyle("-fx-text-fill: white;");

            appointmentGrid.addRow(i, text);

        }

    }

    public void showAppointmentsByDates(String today) {
        System.out.println(today);

        for (AppointmentItem allAppointment : DBDAO.getAllAppointmentsByDate(today)) {

            for (int i = 0; i < timeSlotArrayList.size(); i++) {
                if (allAppointment.getTimeSlot().equals(timeSlotArrayList.get(i))) {
                    for (int j = 0; j < empNameArrayList.size(); j++) {
                        if (allAppointment.getEmpName().equals(empNameArrayList.get(j))) {
                            Label label = new Label("Booked for  " + allAppointment.getClientName()+"\n Contact No: "+allAppointment.getClientMobileNumber());
                            label.setStyle("-fx-text-fill: red;-fx-font-weight: bold; -fx-background-color: white;");

                            label.setFont(new Font(12));
                            label.setTextAlignment(TextAlignment.CENTER);
                            
                            appointmentGrid.add((label), j, i);
                        }
                    }
                }
            }

        }
    }

    @FXML
    private void appointmentOnDateAction(ActionEvent event) {

        try {
            if (!dateField.getEditor().getText().isEmpty()) {

                DBDAO.putDateforAppointments(dateField.getValue().toString());
                Stage closeStage = (Stage) appointmentGrid.getScene().getWindow();
                closeStage.close();
                AnchorPane root = FXMLLoader.load(getClass().getResource("MainDashBoard.fxml"));

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                new FadeInDownBig(root).play();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Date Field cannot be Blank.Please Select Some Date");
                alert.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(MainDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showTodayAppointmentsAction(ActionEvent event) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate today = LocalDate.now();
            today.format(formatter);

            DBDAO.putDateforAppointments(today.toString());

            Stage closeStage = (Stage) appointmentGrid.getScene().getWindow();
            closeStage.close();
            AnchorPane root = FXMLLoader.load(getClass().getResource("MainDashBoard.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            new FadeInDownBig(root).play();
        } catch (IOException ex) {
            Logger.getLogger(MainDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void bookAppointmentAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/AppointmentBooking/AppointmentBooking.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SlideDrawerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
