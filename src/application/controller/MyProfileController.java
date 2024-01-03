package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.SceneSwitch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MyProfileController implements Initializable {

	@FXML
    private Button changePersonalInfo;

    @FXML
    private Circle profileIconCircle;
    

    @FXML
    private AnchorPane UserProfileScene;
    
    @FXML
    void ClickOnChangeInfoButton(MouseEvent event) throws IOException {
    	SceneSwitch.switchScene("LoginScreen.fxml", event);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			Image image = new Image("images/profie icon.png",false);
			profileIconCircle.setFill(new ImagePattern(image));
	}

}
