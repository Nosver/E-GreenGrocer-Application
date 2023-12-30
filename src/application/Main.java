package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

// SQL imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Main java
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
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
    private static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";

    // database username and password
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1A2b3c4d5e.";

    public static void connectDatabase() {
        try {
            // load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // create a connection object
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // create a statement object
            Statement statement = connection.createStatement();

            // execute a query and get the result set
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");

            // print the result set
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("ID") + " " + resultSet.getString("FIRST_NAME") + " " + resultSet.getString("LAST_NAME"));
            }

            // close the result set, statement and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            // handle the exception
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		connectDatabase();
		
		launch(args);
	}
}

