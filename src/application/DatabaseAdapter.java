
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import application.model.Product;
import application.model.User;

public class DatabaseAdapter implements Crud{
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
    
    @Override
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
                        String storedId = resultSet.getString("id");
                        // Create a User object with the retrieved data
                        User user = new User(Integer.parseInt(storedId),storedUsername,storedPassword,storedEMail,storedRole,storedAddress);
                        
                        return user;
                    }
                }
            }
        

        return null; 
        
    }
    
    @Override
    public  void resetPassword(User user , String password) throws SQLException {
    		
            String updateQuery = "UPDATE oop3.users SET password = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            	Integer userid = user.getId();
            	preparedStatement.setString(1, password);
            	preparedStatement.setString(2, userid.toString());
            	System.out.println(userid);
            	preparedStatement.executeUpdate();
            }
    }
    
    

    
    @Override
    public  void insertUser(User user) throws SQLException {
        
            String query = "INSERT INTO oop3.users (name, password, email, role ,address) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getRole());
                preparedStatement.setString(5, user.getAddress());
                

                preparedStatement.executeUpdate();
            }
        
       
    }
    
    @Override
    public void UpdateUser(User user) throws SQLException {
    	 String updateQuery = "UPDATE oop3.users SET name=?, password=?, email=?, address=? WHERE id=?";
    	try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getAddress());
            
            preparedStatement.setInt(5, user.getId());
            
            preparedStatement.executeUpdate();
    	}
    	
    }
    
    @Override
    public  ArrayList<Product> getAllProducts() throws SQLException{
        
            String query = "SELECT * FROM oop3.product";

            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){

                ArrayList<Product> products = new ArrayList<Product>();

                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while(resultSet.next()){
                        // Retrieve product data from the result set
                        String storedProductName = resultSet.getString("name");
                        double storedStock = resultSet.getDouble("stock");
                        double storedPrice = resultSet.getDouble("price");
                        double storedThreshold =resultSet.getDouble("threshold");
                        String storedImagePath= resultSet.getString("imagePath");
                        
                        Product product = new Product(storedProductName, storedStock, storedPrice,storedThreshold,storedImagePath);
                        
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
    
    @Override
    public  ArrayList<Product> getAllProductsWithId() throws SQLException{
        
            String query = "SELECT * FROM oop3.product";

            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){

                ArrayList<Product> products = new ArrayList<Product>();

                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while(resultSet.next()){
                        Integer storedId= resultSet.getInt("id");
                        String storedProductName = resultSet.getString("name");
                        double storedStock = resultSet.getDouble("stock");
                        double storedPrice = resultSet.getDouble("price");
                        double storedThreshold =resultSet.getDouble("threshold");
                        String storedImagePath= resultSet.getString("imagePath");
                        
                        Product product = new Product(storedId,storedProductName, storedStock, storedPrice,storedThreshold,storedImagePath);
                        
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

	@Override
	public void insertProduct(Product product) throws SQLException {
		 String sql = "INSERT INTO Product (name, stock, price, threshold, imagePath) VALUES (?, ?, ?, ?, ?)";

	        try(PreparedStatement statement = connection.prepareStatement(sql)) {
	            // Set the parameters for the prepared statement
	            statement.setString(1, product.getName());
	            statement.setDouble(2, product.getStock());
	            statement.setDouble(3, product.getPrice());
	            statement.setDouble(4, product.getThreshold());
	            statement.setString(5, product.getImagePath());

	            
	            statement.executeUpdate();
	            System.out.println("Product inserted successfully");
	        }catch (SQLException e) {
	            
	            System.err.println("Error inserting product: " + e.getMessage());
	            
	        }
		
	}

	@Override
	public void deleteProduct(Product product) throws SQLException {
		String sql1 = "DELETE FROM oop3.chartitem WHERE productId = ?";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
			 preparedStatement.setInt(1, product.getId());
			 preparedStatement.executeUpdate();
		}catch (SQLException e) {
            e.printStackTrace();
            
        }
		
		
		 String sql2 = "DELETE FROM product WHERE id = ?";
		 try(PreparedStatement statement = connection.prepareStatement(sql2)){
			 statement.setInt(1, product.getId());
			 statement.executeUpdate();
		 }catch (SQLException e) {
	            e.printStackTrace();
	            
	        }
	}

}