/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biktol;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Biktol
 */
public class MainController implements Initializable {
    
    @FXML
    private VBox vbox;
    private Parent fxml;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
            }
        
        });
        
    }    
    
    @FXML
    private void openSignIn(ActionEvent event){
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
            }
        
        });
    }
    
    @FXML
    private void openSignUp(ActionEvent event){
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
            }
        
        });
    }
}
