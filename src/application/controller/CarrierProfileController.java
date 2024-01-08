package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.SceneSwitch;
import application.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class CarrierProfileController implements Initializable{

    @FXML
    private AnchorPane UserProfileScene;

    @FXML
    private Button backButton;

    @FXML
    private Button changePersonalInfo;

    @FXML
    private Text emailField;

    @FXML
    private Label nameField;

    @FXML
    private Text passwordField;

    @FXML
    private Circle profileIconCircle;
    
    private User user;

    public void setUser(User user) {
		this.user = user;
		nameField.setText(user.getName());
		emailField.setText(user.getEmail());
		passwordField.setText(user.getPassword());
		
	}

	@FXML
    void ClickOnChangeInfoButton(MouseEvent event) {
    	SceneSwitch.switchScene("UpdateCarrierInfoScreen.fxml", event, user);
    }

    @FXML
    void backButtonPressed(MouseEvent event) {
    		SceneSwitch.switchScene("CourrierHomeScreen.fxml", event, user);
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
			Image image = new Image("images/profie icon.png",false);
			profileIconCircle.setFill(new ImagePattern(image));
	}
}
