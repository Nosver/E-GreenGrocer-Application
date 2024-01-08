package application.controller;

import java.io.IOException;
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

public class MyProfileController implements Initializable {

	 @FXML
	    private AnchorPane UserProfileScene;

	    @FXML
	    private Text addressField;
	    
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
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		addressField.setText(user.getAddress());
		nameField.setText(user.getName());
		emailField.setText(user.getEmail());
		passwordField.setText(user.getPassword());
		
	}

	@FXML
    void ClickOnChangeInfoButton(MouseEvent event) throws IOException {
    	SceneSwitch.switchScene("UpdateUserInfo.fxml", event, this.user);
    }
	@FXML
    void backButtonPressed(MouseEvent event) {
		SceneSwitch.switchScene("Customer.fxml", event, this.user);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
			Image image = new Image("images/profie icon.png",false);
			profileIconCircle.setFill(new ImagePattern(image));
	}
	
	

}
