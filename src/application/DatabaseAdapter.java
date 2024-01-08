
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import application.model.Chart;
import application.model.Product;
import application.model.User;

public class DatabaseAdapter implements Crud{
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/oop3";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1A2b3c4d5e.";
	private static final Exception SQLException = null;

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
    public  String getUserNameByID(int id) throws SQLException {
        
            String query = "SELECT * FROM oop3.users where id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, String.valueOf(id));

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Retrieve user data from the result set
                        String storedUsername = resultSet.getString("name");
                        
                        return storedUsername;
                    }
                }
            }
        

        return null; 
        
    }
    
    @Override
    public  String getCustomerAddressByID(int id) throws SQLException {
        
            String query = "SELECT * FROM oop3.users where id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, String.valueOf(id));

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Retrieve user data from the result set
                        String customerAdress = resultSet.getString("address");
                        
                        return customerAdress;
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
    public void UpdateChartState(Chart chart) throws SQLException {
    	 String updateQuery = "UPDATE oop3.Chart SET state = ? WHERE chartId = ?";
    	 
    	try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

    		preparedStatement.setInt(2, chart.getChartId());
    		
            preparedStatement.setString(1, chart.getState());
            
            
            preparedStatement.executeUpdate();
    	}
    	
    }
    
    public List<Chart> getPurchasedCharts() throws SQLException {
    	String query = "SELECT * FROM oop3.chart WHERE state = 'purchased'";



        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Chart> purchasedCharts = new ArrayList<>();

                while (resultSet.next()) {
                	int chartId = resultSet.getInt("chartId");
                    int userId = resultSet.getInt("userId");
                    double totalPrice = resultSet.getDouble("totalPrice");
                    String state = resultSet.getString("state");
                    LocalDateTime date = resultSet.getObject("date", LocalDateTime.class);

                    Chart chart = new Chart(chartId, userId, totalPrice, state, date);
                    purchasedCharts.add(chart);
                }

                return purchasedCharts;
            }
        }
    }
    
    @Override
    public ArrayList<Chart> getActiveChart() throws SQLException {  // bunu kontrol et list e gerek yok aslında
    	String query = "SELECT * FROM oop3.chart WHERE state = 'active'";



        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
            	ArrayList<Chart> activeCharts = new ArrayList<>();

                while (resultSet.next()) {
                	int chartId = resultSet.getInt("chartId");
                    int userId = resultSet.getInt("userId");
                    double totalPrice = resultSet.getDouble("totalPrice");
                    String state = resultSet.getString("state");
                    LocalDateTime date = resultSet.getObject("date", LocalDateTime.class);

                    Chart chart = new Chart(chartId, userId, totalPrice, state, date);
                    activeCharts.add(chart);
                }

                return activeCharts;
            }
        }
    }
    

@Override
public ArrayList<Pair<Product, Double>> getProductIdByChartId(int chartId) throws SQLException {
    String query = "SELECT p.*, ci.quantity FROM oop3.product p " +
                   "JOIN oop3.chartitem ci ON p.id = ci.productId " +
                   "WHERE ci.chartId = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, chartId);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            ArrayList<Pair<Product, Double>> productsWithQuantity = new ArrayList<>();

            while (resultSet.next()) {
                int productId = resultSet.getInt("id");
                String productName = resultSet.getString("name");
                double stock = resultSet.getDouble("stock");
                double price = resultSet.getDouble("price");
                double threshold = resultSet.getDouble("threshold");
                String imagePath = resultSet.getString("imagePath");
                double quantity = resultSet.getDouble("quantity");

                Product product = new Product(productId, productName, stock, price, threshold, imagePath);
                Pair<Product, Double> productWithQuantity = new Pair<>(product, quantity);
                productsWithQuantity.add(productWithQuantity);
            }

            return productsWithQuantity;
        }
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
                    	int storedProductID = resultSet.getInt("id");
                        String storedProductName = resultSet.getString("name");
                        double storedStock = resultSet.getDouble("stock");
                        double storedPrice = resultSet.getDouble("price");
                        double storedThreshold =resultSet.getDouble("threshold");
                        String storedImagePath= resultSet.getString("imagePath");
                        
                        Product product = new Product(storedProductID, storedProductName, storedStock, storedPrice,storedThreshold,storedImagePath);
                        
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
    public String getProductNameByProductId(int productId) throws SQLException {
        String query = "SELECT name FROM oop3.product WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve product name from the result set
                    return resultSet.getString("name");
                }
            }
        }

        return null;
    }
    
    @Override
    public Double getProductPriceByProductId(int productId) throws SQLException {
        String query = "SELECT price FROM oop3.product WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve product name from the result set
                    return resultSet.getDouble("price");
                }
            }
        }

        return null;
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

	@Override
	public void UpdateProductById(Product product) throws SQLException {
   	 String updateQuery = "UPDATE oop3.product SET name=?, stock=?, price=?, threshold=?, imagePath=? WHERE id=?";
   	 try(PreparedStatement statement = connection.prepareStatement(updateQuery)){
   		 statement.setString(1,product.getName());
   		 statement.setDouble(2, product.getStock());
   		 statement.setDouble(3, product.getPrice());
   		 statement.setDouble(4, product.getThreshold());
   		 statement.setString(5,product.getImagePath());
   		 statement.setInt(6,product.getId());
   		 
   		statement.executeUpdate();
	   	 }catch (SQLException e) {
	         e.printStackTrace();
	         
	     }
		
	}
	
	
	

	@Override
	public ArrayList<User> getAllCarriers() throws SQLException {
		String query = "SELECT * FROM oop3.users WHERE role ='carrier' ";
	    
	    try (PreparedStatement statement = connection.prepareStatement(query);
	         ResultSet resultSet = statement.executeQuery()) {

	        ArrayList<User> carriers = new ArrayList<>();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String name = resultSet.getString("name");
	            String role = resultSet.getString("role");
	            String password = resultSet.getString("password");
	            String email = resultSet.getString("email");
	            String address = resultSet.getString("address");

	            User carrier = new User(id, name, password, email, role, address);
	            carriers.add(carrier);
	        }

	        return carriers;
	    }
	}

	@Override
	public void deleteUser(User user) throws SQLException {
		String deleteStatement = "DELETE FROM oop3.users WHERE id = ?";
		
		 try (PreparedStatement statement = connection.prepareStatement(deleteStatement)){
			 statement.setInt(1,user.getId());
			 statement.executeUpdate();
		 }catch (SQLException e) {
	         e.printStackTrace();
	         
	     }
		
	}
	
	// Creates chart in database
	public void createChart(User user) throws SQLException {
		
		String chartQuery = "INSERT INTO oop3.chart (userId,totalPrice,state,date) VALUES (?,?,?,?)";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(chartQuery)) {
            
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setDouble(2, 0);
            preparedStatement.setString(3, "onChart");
            preparedStatement.setDate(4, null);
            
            preparedStatement.executeUpdate();
        }
		System.out.println("Chart is created");
	}
	
	/*
	 * Check if chart exist
	 * - if exist reutrn
	 * - if not create go to first
	 */
	
	public boolean doesChartExist(int userId) {
        String chartQuery = "SELECT * FROM oop3.chart WHERE userId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(chartQuery)) {
            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if a row is found
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's needs
            return false; // Return false in case of an exception
        }
    }
	
	public Chart getChart(User user) { 
		
		String chartQuery = "SELECT * FROM oop3.chart WHERE userId = ?";
		chartQuery = "SELECT * FROM oop3.chart WHERE userId = ? AND state = ?";
		
		Chart chart = new Chart();
		
		try (PreparedStatement statement = connection.prepareStatement(chartQuery)){
			
			 // If user's chart does not exist create one
			 if(!doesChartExist(user.getId())) {
				createChart(user);
			 }
			 
			 // Pull chart from database
			 System.out.println("Pulling Chart data from DB");
			 
			 statement.setInt(1, user.getId());
			 statement.setString(2, "onChart");
			 
			 try (ResultSet resultSet = statement.executeQuery()) {
                 if (resultSet.next()) {
                	 
                     int storedUserId = resultSet.getInt("userId");
                     double storedTotalPrice = resultSet.getDouble("totalPrice");
                     String storedState = resultSet.getString("state");
                     String storedDateStr = resultSet.getString("date");
                     int storedChartId = resultSet.getInt("chartId");
                    
                     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                     LocalDateTime dateTime = (storedDateStr != null) ? LocalDateTime.parse(storedDateStr, formatter) : null;
                     
                     
                     chart.setUserId(storedUserId);
                     chart.setTotalPrice(storedTotalPrice);
                     chart.setState(storedState);
                     chart.setDate(dateTime);
                     chart.setChartId(storedChartId);                 
                 }
             }
			 
		 }catch (SQLException e) {
	         e.printStackTrace();
	     }
		 return chart;
	}
	
	public boolean isStockSufficient(Product product, double quantity) throws Exception {
		
		String checkQuery = "SELECT * FROM oop3.product WHERE id = ?";
		PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
		checkStatement.setInt(1, product.getId());
		
		ResultSet resultSet = checkStatement.executeQuery();
		
		if (!resultSet.next()) {throw SQLException;}
		
		double stock = resultSet.getDouble("stock");
		
		if(stock <= quantity) {return false;}
		return true;				
	}
	
	public void insertChartItem(Product product, double quantity, Chart chart) throws SQLException {
		
	    // Check if there is the same product in the same customer's chart
	    String checkQuery = "SELECT * FROM oop3.chartitem WHERE chartId = ? AND productId = ?";
	    
	    try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
	        checkStatement.setInt(1, chart.getChartId());
	        checkStatement.setInt(2, product.getId());

	        try (ResultSet resultSet = checkStatement.executeQuery()) {
	            if (resultSet.next()) {
	                // Product already exists in the chart, update the quantity
	                double existingQuantity = resultSet.getDouble("quantity");
	                double newQuantity = existingQuantity + quantity;

	                String updateQuery = "UPDATE oop3.chartitem SET quantity = ? WHERE chartId = ? AND productId = ?";
	                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
	                    updateStatement.setDouble(1, newQuantity);
	                    updateStatement.setInt(2, chart.getChartId());
	                    updateStatement.setInt(3, product.getId());
	                    updateStatement.executeUpdate();
	                }

	                System.out.println("Item quantity updated in DB table. Product ID: " + product.getId());
	            } else {
	                // Product does not exist, insert a new row
	                String insertQuery = "INSERT INTO oop3.chartitem (chartId, productId, quantity) VALUES (?, ?, ?)";
	                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
	                    insertStatement.setInt(1, chart.getChartId());
	                    insertStatement.setInt(2, product.getId());
	                    insertStatement.setDouble(3, quantity);
	                    insertStatement.executeUpdate();
	                }

	                System.out.println("Item inserted into DB table. Product ID: " + product.getId());
	            }
	        }
	    }
	}
	
	private void pullChartInfo(Chart chart) {
		// PULL THIS OVERWRITE INTO CHART OBJ.
		// !!!
	}

	private void updateChartPrice(Chart chart) throws Exception {
		
		// Get product and thier quantity from chart table
        
	      
        // Put into chart obj
        
        // Calculate char prices from obj
        chart.calculatePrice();

		
		String query = "SELECT * FROM oop3.chart WHERE chartId = ?";
		PreparedStatement checkStatement = connection.prepareStatement(query);
		checkStatement.setInt(1, chart.getChartId());
		ResultSet resultSet = checkStatement.executeQuery();
		
		if (!resultSet.next()) {throw SQLException;}
		        
        String updateQuery = "UPDATE oop3.chart SET totalPrice = ? WHERE chartId = ?";
        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
        
        updateStatement.setDouble(1, chart.getTotalPrice());
        updateStatement.setInt(2, chart.getChartId());
        updateStatement.executeUpdate();
	}
	
	public void updateChart(Product product, double quantity, Chart chart) throws Exception{
		
		insertChartItem(product, quantity, chart);
		
		
		
		updateChartPrice(chart);
		
		reduceStock(product, quantity);
		
	}
	
	// If chart is deleted restore the stock in DB
	public void restoreStock(Product product, double quantity) {
		
	}
	
	// Reduce the stock when purchased
	public void reduceStock(Product product, double quantity) throws Exception {			// THESHOLD CHECK !!!!!!
		
		String query = "SELECT * FROM oop3.product WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, product.getId());
		
		ResultSet resultSet = statement.executeQuery();
		
		if(!resultSet.next()) { throw SQLException;}	// If prodcut does not exist throw exception
		
		double stock = resultSet.getDouble("stock");
		double updatedStock = stock - quantity;
		
		System.out.println("asdasdadsd");
		// Put updated stock to DB
		String updateQuery = "UPDATE oop3.product SET stock = ? WHERE id = ?";
		PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
		
		product.setStock(updatedStock);
		
		updateStatement.setDouble(1, updatedStock);
		updateStatement.setInt(2, product.getId());
		
		updateStatement.executeUpdate();
	}

	@Override
	public List<Chart> getPurchasedAndActiveCharts() throws SQLException {
		
		String query = "SELECT * FROM oop3.chart WHERE state = 'purchased' OR  state = 'active'";



        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Chart> purchasedCharts = new ArrayList<>();

                while (resultSet.next()) {
                	int chartId = resultSet.getInt("chartId");
                    int userId = resultSet.getInt("userId");
                    double totalPrice = resultSet.getDouble("totalPrice");
                    String state = resultSet.getString("state");
                    LocalDateTime date = resultSet.getObject("date", LocalDateTime.class);

                    Chart chart = new Chart(chartId, userId, totalPrice, state, date);
                    purchasedCharts.add(chart);
                }

                return purchasedCharts;
            }
        }
	}

	@Override
	public List<Chart> getDeliveredCharts() throws SQLException {
		
		String query = "SELECT * FROM oop3.chart WHERE state = 'delivered'";



        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Chart> purchasedCharts = new ArrayList<>();

                while (resultSet.next()) {
                	int chartId = resultSet.getInt("chartId");
                    int userId = resultSet.getInt("userId");
                    double totalPrice = resultSet.getDouble("totalPrice");
                    String state = resultSet.getString("state");
                    LocalDateTime date = resultSet.getObject("date", LocalDateTime.class);

                    Chart chart = new Chart(chartId, userId, totalPrice, state, date);
                    purchasedCharts.add(chart);
                }

                return purchasedCharts;
            }
        }
	}
	
	

	
	
}

