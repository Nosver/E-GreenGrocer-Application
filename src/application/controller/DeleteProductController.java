package application.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.DatabaseAdapter;
import application.SceneSwitch;
import application.model.Product;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class DeleteProductController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Button deleteProductButton;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private Label selectedProductLabel;

    @FXML
    private TableColumn<Product, Double> stockColumn;

    @FXML
    private TableColumn<Product, Double> thresholdColumn;
    
    @FXML
    private TableColumn<Product, Integer> idField;

    private User user;
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    @FXML
    void backButtonClicked(MouseEvent event) {
    		SceneSwitch.switchScene("OwnerScreen.fxml", event, user);
    }

    

	@FXML
    void deleteButtonClicked(MouseEvent event) throws SQLException {
		ObservableList<Product> selectedProducts = productTable.getSelectionModel().getSelectedItems();
		if(selectedProducts.isEmpty())
			return;
		Product toBeDeleted =selectedProducts.get(0);
		System.out.println(toBeDeleted.getId());
		DatabaseAdapter db = new DatabaseAdapter();

		//deleteFile(toBeDeleted.getImagePath());
		db.deleteProduct(toBeDeleted);
		selectedProductLabel.setText(null);
		refreshTable();
    }
	
	private void refreshTable() {
		DatabaseAdapter db= new DatabaseAdapter();
		ArrayList<Product> products= null;
		try {
			products = db.getAllProductsWithId();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			productTable.getItems().clear();
			ObservableList<Product> productList = FXCollections.observableArrayList(products);
	        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
	        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
	        stockColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("stock"));
	        thresholdColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("threshold"));
	        idField.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
	        productTable.setItems(productList);
	}
	
	private void deleteFile(String filePath) {
		Path path = Paths.get(filePath);
		try {
            Files.delete(path);
            System.out.println("Image file deleted successfully.");
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DatabaseAdapter db= new DatabaseAdapter();
		ArrayList<Product> products= null;
		try {
			products = db.getAllProductsWithId();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
			ObservableList<Product> productList = FXCollections.observableArrayList(products);
		 	
	        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
	        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
	        stockColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("stock"));
	        thresholdColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("threshold"));
	        idField.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
	        productTable.setItems(productList);

		
	}
	
	@FXML
    void tableClicked(MouseEvent event) {
		Product product = productTable.getSelectionModel().getSelectedItem();
		selectedProductLabel.setText(product.getName());
    }
	

}
