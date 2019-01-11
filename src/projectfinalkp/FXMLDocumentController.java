/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectfinalkp;

import DatabaseHelper.DBDAO;
import DatabaseHelper.DBUtil;
import animatefx.animation.FadeInDownBig;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Omskamate
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private AnchorPane loginscreen;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        String user = usernameField.getText().trim();
        String pass = passwordField.getText().trim();
        String admin = "admin";
        if (user.equals(admin) && pass.equals(admin)) {
            try {

                Stage clStage = (Stage) usernameField.getScene().getWindow();
                clStage.close();

                AnchorPane root = FXMLLoader.load(getClass().getResource("/MainDashBoard/MainDashBoard.fxml"));

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                // stage.initStyle(StageStyle.UNDECORATED);
                stage.show();

                new FadeInDownBig(root).play();

            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        today.format(formatter);

        DBDAO.putDateforAppointments(today.toString());

    }

}
