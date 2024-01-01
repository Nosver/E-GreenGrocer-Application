package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


// Main java
public class Main extends Application {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/book";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1A2b3c4d5e.";

	@Override
	public void start(Stage primaryStage) {
	    try {
	        // Load Login Screen FXML file
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/Login Screen.fxml"));
	        Parent root = loader.load();

	        // Set the main FXML file as the scene
	        Scene scene = new Scene(root);

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
