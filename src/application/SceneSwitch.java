package application;

import java.io.IOException;

import application.controller.CourrierHomeScreenController;
import application.controller.CourrierOrderScreenController;
import application.controller.CreateNewProductController;
import application.controller.CustomerScreenController;
import application.controller.DeleteProductController;
import application.controller.FireCarrierController;
import application.controller.ForgotPasswordScreenController1;
import application.controller.ForgotPasswordScreenController2;
import application.controller.HireCarrierController;
import application.controller.MyProfileController;
import application.controller.OwnerController;
import application.controller.ProductItemController;
import application.controller.UpdateProductController;
import application.controller.UpdateUserInfoController;
import application.controller.productScreenController;
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
                else if("updateUserInfo.fxml".equals(fxmlFileName)) {
                	UpdateUserInfoController controler = loader.getController();
                	controler.setUser(user);
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
                else if("CourrierHomeScreen.fxml".equals(fxmlFileName)){
                	CourrierHomeScreenController controller = loader.getController();
                	controller.setUser(user);
                }
                else if("UpdateProductScreen.fxml".equals(fxmlFileName)){
                	UpdateProductController controller = loader.getController();
                	controller.setUser(user);
                }
                else if("FireCarrierScreen.fxml".equals(fxmlFileName)){
                	FireCarrierController controller = loader.getController();
                	controller.setUser(user);
                }
            	
                else if("CourrierOrderScreen.fxml".equals(fxmlFileName)){
                	CourrierOrderScreenController controller = loader.getController();
                	controller.setUser(user);
                }
                else if("HireCarrierScreen.fxml".equals(fxmlFileName)){
                	HireCarrierController controller = loader.getController();
                	controller.setUser(user);
                }
                else if ("newProductScreen.fxml".equals(fxmlFileName)) {
                    productScreenController controller = loader.getController();
                    controller.setUser(user); // Pass the user to the controller
                }

            	
            }
            
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            if("newProductScreen.fxml".equals(fxmlFileName))
            	stage.setResizable(true);
            else
            	stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
