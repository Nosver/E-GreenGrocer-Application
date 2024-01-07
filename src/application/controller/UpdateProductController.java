package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.DatabaseAdapter;
import application.SceneSwitch;
import application.model.Product;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class UpdateProductController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Product, Integer> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TextField priceField;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private Label selectedProductLabel;

    @FXML
    private TableColumn<Product, Double> stockColumn;

    @FXML
    private TextField stockField;

    @FXML
    private TableColumn<Product, Double> thresholdColumn;

    @FXML
    private TextField thresholdField;

    @FXML
    private Button updateButton;
    
    @FXML
    private ImageView imageField;
    
    @FXML
    private Button chooseImageButton;
    
    private String imageFilePath;
    
    private String imageFileName;
    
    @FXML
    void chooseImageButton(MouseEvent event) throws MalformedURLException {
    	FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose an image");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All images", "*.svg","*.png","*.jpg","*.jpeg"),	
				new FileChooser.ExtensionFilter("JPG Image", "*.jpg"),
				new FileChooser.ExtensionFilter("JPEG Image", "*.jpeg"),
				new FileChooser.ExtensionFilter("PNG Image", "*.png"),
				new FileChooser.ExtensionFilter("SVG Image", "*.svg")
				
				);
		File selectedFile=fileChooser.showOpenDialog(null);
		if(selectedFile!= null) { 
			
			Image image = new Image(selectedFile.toURI().toURL().toString());
			imageField.setImage(image);
			this.imageFilePath=selectedFile.getAbsolutePath();
			this.imageFileName=selectedFile.getName();
			System.out.println(imageFilePath);
		}
    }
    
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
    void updateButtonClicked(MouseEvent event) throws SQLException {
    	if(productTable.getSelectionModel().getSelectedItem()==null) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Please select a Product to update");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	Product SelectedProduct= productTable.getSelectionModel().getSelectedItem();
    	double price;
		double stock;
		double threshold;
		String name;
    	if(stockField.getText().isBlank() || nameField.getText().isBlank() || priceField.getText().isBlank() || thresholdField.getText().isBlank() || imageFileName==null) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Each field including image must be filled");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	
    	//stock check
		try {
			stock=Double.parseDouble(stockField.getText());
			if(stock<=0) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
    			alert.setTitle("error");
    			alert.setContentText("stock can not be negative or zero! Try again.");
    			Optional<ButtonType>	result = alert.showAndWait();
    			return;
			}
		}catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Invalid stock! Try again.");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
		}
		
		
		
		try {
			price=Double.parseDouble(priceField.getText());
			if(price<=0) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
    			alert.setTitle("error");
    			alert.setContentText("price can not be negative or zero! Try again.");
    			Optional<ButtonType>	result = alert.showAndWait();
    			return;
			}
		}catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Invalid price! Try again.");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
		}
		
		//threshold check
		try {
			threshold=Double.parseDouble(thresholdField.getText());
			if(threshold<=0) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
    			alert.setTitle("error");
    			alert.setContentText("Threshold can not be negative or zero! Try again.");
    			Optional<ButtonType>	result = alert.showAndWait();
    			return;
			}
		}catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Invalid threshold! Try again.");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
		}
		if(threshold >= stock) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("threshold can not be greater than stock");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
		}
		name=nameField.getText();
		SelectedProduct.setName(name);
		SelectedProduct.setImagePath("images/"+imageFileName);
		SelectedProduct.setThreshold(threshold);
		SelectedProduct.setPrice(price);
    	SelectedProduct.setStock(stock);
    	System.out.println(SelectedProduct.getId());
    	DatabaseAdapter db = new DatabaseAdapter();
    	db.UpdateProductById(SelectedProduct);
    	saveImage(imageFilePath,"src/images");
    	
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Successfully updated");
		alert.setContentText("product Successfully updated");
		Optional<ButtonType>	result = alert.showAndWait();
		
		imageField.setImage(null);
		selectedProductLabel.setText(null);
		nameField.setText(null);
		priceField.setText(null);
		stockField.setText(null);
		thresholdField.setText(null);
		nameField.setPromptText(null);
		priceField.setPromptText(null);
		stockField.setPromptText(null);
		thresholdField.setPromptText(null);
		
    	
    	refreshTable();
    }
    
    public void refreshTable() {
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
	        idColumn.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
	        productTable.setItems(productList);
		
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
	        idColumn.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
	        productTable.setItems(productList);
		
	}
	
	@FXML
    void tableClicked(MouseEvent event) {
		
		Product product = productTable.getSelectionModel().getSelectedItem();
		selectedProductLabel.setText(product.getName());
		nameField.setPromptText(product.getName());
		priceField.setPromptText(String.valueOf(product.getPrice()));
		stockField.setPromptText(String.valueOf(product.getStock()));
		thresholdField.setPromptText(String.valueOf(product.getThreshold()));
    }
	
	 public boolean saveImage(String imagePath, String folderPath) {
	        byte[] content;
	        try {
	            content = Files.readAllBytes(Paths.get(imagePath));
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }

	        
	        String fileName = Paths.get(imagePath).getFileName().toString();

	        Path folder = Paths.get(folderPath);

	        if (!Files.exists(folder)) {
	            try {
	                Files.createDirectories(folder);
	            } catch (IOException e) {
	                e.printStackTrace();
	                return false;
	            }
	        }

	        Path filePath = folder.resolve(fileName);

	        try {
	            Files.write(filePath, content);
	            System.out.println("Image saved successfully: " + filePath);
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.err.println("Error saving the image.");
	            return false;
	        }
	    }

}
