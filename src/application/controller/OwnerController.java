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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class OwnerController implements Initializable{
	private User user;
	
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@FXML
    private Button logOutButton;

	@FXML
    private Button ViewActiveOrdersButton;

    @FXML
    private Button addProductButton;

    @FXML
    private Button employCarrierButton;

    @FXML
    private Button fireCarrierButton;

    @FXML
    private ImageView imageField;

    @FXML
    private Button removeProductButton;

    @FXML
    private Button updateOwnerInfoButton;

    @FXML
    private Button updateProductButton;

    @FXML
    private Button viewPastOrdersButton;

    @FXML
    private Label welcomeLabel;

    public Label getWelcomeLabel() {
    	return this.welcomeLabel;
    }
    @FXML
    void ViewActiveOrdersButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("ViewActiveOrders.fxml", event, user);
    }

    @FXML
    void addProductPressed(MouseEvent event) {
    		SceneSwitch.switchScene("CreateNewProductScreen.fxml", event, user);
    }

    @FXML
    void employCarrierButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("HireCarrierScreen.fxml", event, user);
    }

    @FXML
    void fireCarrierButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("FireCarrierScreen.fxml", event, user);
    }

    @FXML
    void removeProductClicked(MouseEvent event) {
    	SceneSwitch.switchScene("deleteProductScreen.fxml", event, user);
    }

    @FXML
    void updateOwnerInfoClicked(MouseEvent event) {
    	SceneSwitch.switchScene("UpdateOwnerInfo.fxml", event, user);
    }

    @FXML
    void updateProductClicked(MouseEvent event) {
    	SceneSwitch.switchScene("UpdateProductScreen.fxml", event, user);
    }

    @FXML
    void viewPastOrdersButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("ViewPastOrders.fxml", event, user);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Image image = new Image("images/owner.png",false);
		imageField.setImage(image);
		
	}

	@FXML
    void logOutButtonClicked(MouseEvent event) {
			SceneSwitch.switchScene("LoginScreen.fxml", event, null);
    }
}
