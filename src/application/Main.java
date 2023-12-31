package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// Main java
public class Main extends Application {
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/book";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1A2b3c4d5e.";
	
	@Override
	public void start(Stage primaryStage) {
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
	                        System.out.println("id " + column1Value + ", book_name: " + column2Value + ", isbn_number:" + column3Value);
	                    }
	                }
	            }
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// connection URL
    
    
	
	public static void main(String[] args) {
		
		
		launch(args);
	}
}

