
package com.fb.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Biktol
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private Button loginBtn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openRegistrationForm(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/fb/views/Register.fxml"));
        
        Scene scene = loginBtn.getScene();
        
        root.translateXProperty().set(scene.getWidth());
        
        StackPane parentContainer = (StackPane)scene.getRoot();
        parentContainer.getChildren().add(root);
        
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(eventl ->{
            parentContainer.getChildren().remove(container);
        });
        timeline.play();
    }
    
}
