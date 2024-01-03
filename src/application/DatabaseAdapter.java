
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.User;

public class DatabaseAdapter {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/oop3";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1A2b3c4d5e.";

    private static Connection connection;

   public DatabaseAdapter() {
	   getConnection();
   }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    
    public static User getUserByEmail(String email) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM oop3.users where email = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Retrieve user data from the result set
                        String storedUsername = resultSet.getString("name");
                        String storedPassword = resultSet.getString("password");
                        String storedEMail = resultSet.getString("email");
                        String storedAddress = resultSet.getString("address");
                        String storedRole = resultSet.getString("role");
                        // Create a User object with the retrieved data
                        User user = new User(storedUsername,storedPassword,storedEMail,storedAddress,storedRole);
                        
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; 
        
    }
    
    
    public static  void resetPassword(User user , String password) {
    	
        try (Connection connection = getConnection()) {
        	
            String updateQuery = "UPDATE users SET password = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            	Integer userid = user.getId();
            	preparedStatement.setString(1, password);
            	preparedStatement.setString(2, userid.toString());
            	preparedStatement.executeUpdate();
            }

        	
        }catch (SQLException e) {
            e.printStackTrace();
        }
    	
    }
    
    public static void insertUser(User user) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO oop3.users (name, password, email, address, role) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getAddress());
                preparedStatement.setString(5, user.getRole());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }
    
    
    
    
    
    
}