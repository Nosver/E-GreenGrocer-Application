
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import application.model.Product;
import application.model.User;

public class DatabaseAdapter {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/oop3";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1A2b3c4d5e.";

    private static Connection connection;

   public DatabaseAdapter() {
	  this.connection= getConnection();
   }

    public Connection getConnection() {
        if (this.connection == null) {
            try {
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    
    public  User getUserByEmail(String email) throws SQLException {
        
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
        

        return null; 
        
    }
    
    
    public  void resetPassword(User user , String password) throws SQLException {
    	
       
        	
            String updateQuery = "UPDATE oop3.users SET password = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            	Integer userid = user.getId();
            	preparedStatement.setString(1, password);
            	preparedStatement.setString(2, userid.toString());
            	preparedStatement.executeUpdate();
            }

        	
       
    	
    }
    
    public  void insertUser(User user) throws SQLException {
        
            String query = "INSERT INTO oop3.users (name, password, email, address, role) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getAddress());
                preparedStatement.setString(5, user.getRole());

                preparedStatement.executeUpdate();
            }
        
       
    }
    
    public  ArrayList<Product> getAllProducts() throws SQLException{
        
            String query = "SELECT * FROM oop3.products";

            /*
             Content of 
                - product_id
                - product_name
                - stock
                - price
             */

            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){

                ArrayList<Product> products = new ArrayList<Product>();

                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while(resultSet.next()){
                        // Retrieve product data from the result set
                        String storedProductName = resultSet.getString("product_name");
                        int storedStock = resultSet.getInt("stock");
                        double storedPrice = resultSet.getDouble("price");
                        // Create a Product object with the retrieved data
                        Product product = new Product(storedProductName, storedStock, storedPrice);
                        // Add the product to the list
                        products.add(product);
                    }
                    
                    /*
                     
	                    for(int i=0; i < products.size(); i++) {
	                    	System.out.println(products.get(i).getName());
	                    }
                   		
                     */
                    return products;
                }
            }
        
    
    }

}