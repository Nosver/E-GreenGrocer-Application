package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
	    try {
	    	Parent root=FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
			Scene scene= new Scene(root);
			// scene.getStylesheets().add(getClass().getResource("/css/Login.css").toExternalForm());
	        primaryStage.setTitle("Main Screen");
	        primaryStage.setScene(scene);
	        primaryStage.setResizable(false);
	        primaryStage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public static void main(String[] args) {	
		launch(args);
	}
}
