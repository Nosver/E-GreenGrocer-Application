package application;

import java.io.IOException;

import application.controller.CreateNewProductController;
import application.controller.CustomerScreenController;
import application.controller.DeleteProductController;
import application.controller.ForgotPasswordScreenController1;
import application.controller.ForgotPasswordScreenController2;
import application.controller.MyProfileController;
import application.controller.OwnerController;
import application.controller.UpdateProductController;
import application.controller.UpdateUserInfoController;
import application.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SceneSwitch {
	public static void switchScene(String fxmlFileName, MouseEvent event, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitch.class.getResource(fxmlFileName));
            Parent root = loader.load();

            if (user != null) {
                // Assume the controller has a setUser method
            	if ("myProfileScreen.fxml".equals(fxmlFileName)) {
                    MyProfileController controller = loader.getController();
                    controller.setUser(user);
                } else if ("customer.fxml".equals(fxmlFileName)) {
                    CustomerScreenController controller = loader.getController();
                    controller.setUser(user);
                }
                else if("ForgotPassword1.fxml".equals(fxmlFileName)){
                	ForgotPasswordScreenController1 controller = loader.getController();
                	controller.setUser(user);
                }
                else if("ForgotPassword2.fxml".equals(fxmlFileName)){
                	ForgotPasswordScreenController2 controller = loader.getController();
                	controller.setUser(user);
                }
                else if("UpdateUserInfo.fxml".equals(fxmlFileName)){
                	UpdateUserInfoController controller = loader.getController();
                	controller.setUser(user);
                }
                else if("OwnerScreen.fxml".equals(fxmlFileName)){
                	OwnerController controller = loader.getController();
                	controller.setUser(user);
                	controller.getWelcomeLabel().setText("Welcome "+user.getName());
                }
                else if("CreateNewProductScreen.fxml".equals(fxmlFileName)){
                	CreateNewProductController controller = loader.getController();
                	controller.setUser(user);
                }
                else if("deleteProductScreen.fxml".equals(fxmlFileName)){
                	DeleteProductController controller = loader.getController();
                	controller.setUser(user);
                }
                else if("UpdateProductScreen.fxml".equals(fxmlFileName)){
                	UpdateProductController controller = loader.getController();
                	controller.setUser(user);
                }
            	
            }
            
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
