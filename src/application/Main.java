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
	    	Parent root=FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
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

	public void loadDatabase() {
		try {

			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			String sql = "SELECT * FROM book";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				// Execute the SELECT statement
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					// Process the result set
					while (resultSet.next()) {
						String column1Value = resultSet.getString("id");
						String column2Value = resultSet.getString("book_name");
						String column3Value = resultSet.getNString("isbn_number");
						// Process the retrieved data as needed
						System.out.println("id " + column1Value + ", book_name: " + column2Value + ", isbn_number:"
								+ column3Value);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		launch(args);
	}
}
