package application.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import application.DatabaseAdapter;
import application.SceneSwitch;
import application.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;

public class HireCarrierController implements Initializable {

    @FXML
    private Button backButton;
    
    @FXML
    private ImageView imageField;

    @FXML
    private TextField emailField;

    @FXML
    private Button hireCarrierButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;
    
    private User user;
    

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@FXML
    void backButtonClicked(MouseEvent event) {
			SceneSwitch.switchScene("OwnerScreen.fxml", event, user);
    }
	
	private static boolean containsUpperCase(String password) {
        return !password.equals(password.toLowerCase());
    }
    private static boolean containsLowerCase(String password) {
        return !password.equals(password.toUpperCase());
    }

    private static boolean containsNonAlphabetic(String password) {
        Pattern pattern = Pattern.compile(".*[^a-zA-Z].*");
        return pattern.matcher(password).matches();
    }

    private static boolean containsDigit(String password) {
        return password.matches(".*\\d.*");
    }
    
    public static boolean isStrongPassword(String password) {
        if (!containsUpperCase(password)) {
            return false;
        }
        
        if (!containsLowerCase(password)) {
            return false;
        }

        if (!containsNonAlphabetic(password)) {
            return false;
        }

        if (!containsDigit(password)) {
            return false;
        }

        return true;
    }

    @FXML
    void hireCarrierButtonClicked(MouseEvent event) throws SQLException {
    	if(nameField.getText().isBlank() || emailField.getText().isBlank() || passwordField.getText().isBlank()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("please fill all the fields");
			Optional<ButtonType>	result = alert.showAndWait();
    		return;
    	}
    	if(!isStrongPassword(passwordField.getText())) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Password is not strong. It should have at least one upper,one lower, one non-alphabetic letter and one number");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	
    	User carrier = new User(nameField.getText(),passwordField.getText(),emailField.getText(),"carrier",null);
    	
    	DatabaseAdapter db= new DatabaseAdapter();
    	
    	User user= db.getUserByEmail(emailField.getText());
    	if(user!=null) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("Error");
    		alert.setContentText("Carrier/Owner/ already exists");
    		Optional<ButtonType>	result = alert.showAndWait();
    		return;
    	}
    	
    	db.insertUser(carrier);
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setContentText("Carrier added succesfully");
		Optional<ButtonType>	result = alert.showAndWait();
		
		emailField.setText(null);   
		nameField.setText(null);
		passwordField.setText(null);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Image image = new Image("images/recep.png",false);
		imageField.setImage(image);
		
	}

}
