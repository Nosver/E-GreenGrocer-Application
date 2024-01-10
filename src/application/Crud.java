package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import application.model.Chart;
import application.model.Product;
import application.model.User;

public interface Crud {
	 public  User getUserByEmail(String email) throws SQLException;
	 public  void resetPassword(User user , String password) throws SQLException;
	 public  void insertUser(User user) throws SQLException;
	 public void UpdateUser(User user) throws SQLException;
	 public  ArrayList<Product> getAllProducts() throws SQLException;
	 public void insertProduct(Product product) throws SQLException;
	String getUserNameByID(int id) throws SQLException;
	 public void deleteProduct(Product product)throws SQLException;
	 public ArrayList<Product> getAllProductsWithId() throws SQLException;
	 public void UpdateProductById(Product product) throws SQLException;
	 public ArrayList<User> getAllCarriers() throws SQLException;
	 public void deleteUser(User user) throws SQLException;
	String getCustomerAddressByID(int id) throws SQLException;
	ArrayList<Chart> getActiveChart() throws SQLException;
	void UpdateChartState(Chart chart) throws SQLException;
	public List<Chart> getPurchasedAndActiveCharts() throws SQLException;
	public List<Chart> getDeliveredCharts() throws SQLException;
	ArrayList<Pair<Product, Double>> getProductIdByChartId(int chartId) throws SQLException;
	String getProductNameByProductId(int productId) throws SQLException;
	Double getProductPriceByProductId(int productId) throws SQLException;
	public  ArrayList<Product> getAllProductsWithStock() throws SQLException;
	public Chart getChartByUserId(int userId) throws SQLException;
	public boolean stockCheck(Product product, double currentOnChart, double updated ) throws SQLException;
	Product getProductByProductName(String name) throws SQLException;
	void UpdateProductByIdNew(Product product) throws SQLException;
	List<Chart> getPurchasedActiveAndDeliveredCharts(User user) throws SQLException;
	public void deleteChartByChartId(int id) throws SQLException;
}
