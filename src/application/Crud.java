package application;

import java.sql.SQLException;
import java.util.ArrayList;

import application.model.Product;
import application.model.User;

public interface Crud {
	 public  User getUserByEmail(String email) throws SQLException;
	 public  void resetPassword(User user , String password) throws SQLException;
	 public  void insertUser(User user) throws SQLException;
	 public void UpdateUser(User user) throws SQLException;
	 public  ArrayList<Product> getAllProducts() throws SQLException;
	 public void insertProduct(Product product) throws SQLException;
	 public void deleteProduct(Product product)throws SQLException;
	 public ArrayList<Product> getAllProductsWithId() throws SQLException;
}
