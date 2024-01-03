package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


// Main java
public class Main extends Application {

	/*
	 * 
	-- userinfo ve update ekranı ve backend // Masis

	-- forgot password  //doğukan

	-- sign up  //yiğit

	-- product ekranı //kemal

	 
	 * */

	@Override
	public void start(Stage primaryStage) {
	    try {
	        // Load Login Screen FXML file
	    	Parent root=FXMLLoader.load(getClass().getResource("ForgotPassword1.fxml"));
			Scene scene= new Scene(root);

	        // Set the main FXML file as the scene
	       

	        // Set the stage title and scene
	        primaryStage.setTitle("Main Screen");
	        primaryStage.setScene(scene);

	        // Show the main screen
	        primaryStage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public static void main(String[] args) {
		
		launch(args);
	}
}
