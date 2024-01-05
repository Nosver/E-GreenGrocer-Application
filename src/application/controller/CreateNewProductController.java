package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Optional;

import application.DatabaseAdapter;
import application.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class CreateNewProductController {

    @FXML
    private TextField StockField;

    @FXML
    private Button addProductButton;

    @FXML
    private Button backButton;

    @FXML
    private Button chooseImageButton;

    @FXML
    private ImageView imageField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField tresholdField;
    
    private String imageFileName;
    
    private String imageFilePath;

    @FXML
    void addProductButtonClicked(MouseEvent event) throws SQLException {
    		double price;
    		double stock;
    		double threshold;
    		String name;
    		if(StockField.getText().isBlank() || nameField.getText().isBlank() || priceField.getText().isBlank() || tresholdField.getText().isBlank() || imageFileName==null) {
    			Alert alert = new Alert(Alert.AlertType.WARNING);
    			alert.setTitle("error");
    			alert.setContentText("Each field must be filled");
    			Optional<ButtonType>	result = alert.showAndWait();
    			return;
    			
    		}
    		//stock check
    		try {
    			stock=Double.parseDouble(StockField.getText());
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
    			threshold=Double.parseDouble(tresholdField.getText());
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
    		Product product = new Product(name,stock,price,threshold,"images\\"+imageFileName); 
    		System.out.println(product);
    		saveImage(imageFileName,"src/images");
    		DatabaseAdapter db = new DatabaseAdapter();
    		db.insertProduct(product);
    		
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setContentText("Product Successfully added");
			Optional<ButtonType>	result = alert.showAndWait();

    		
    		
    		
    }

    @FXML
    void backButtonClicked(MouseEvent event) {

    }

    @FXML
    void chooseImageButtonClicked(MouseEvent event) throws MalformedURLException {
    		
    		FileChooser fileChooser = new FileChooser();
    		fileChooser.setTitle("Choose an image");
    		//fileChooser.setInitialDirectory(new File("C:"));
    		fileChooser.getExtensionFilters().addAll(
    				new FileChooser.ExtensionFilter("All images", "*.svg","*.png","*.jpg"),	
    				new FileChooser.ExtensionFilter("JPG Image", "*.jpg"),
    				new FileChooser.ExtensionFilter("JPEG Image", "*.jpeg"),
    				new FileChooser.ExtensionFilter("PNG Image", "*.png"),
    				new FileChooser.ExtensionFilter("SVG Image", "*.svg")
    				
    				);
    		File selectedFile=fileChooser.showOpenDialog(null);
    		if(selectedFile!= null) { ///
    			Image image = new Image(selectedFile.toURI().toURL().toString());
    			System.out.println(selectedFile.getPath());
    			System.out.println(selectedFile.getAbsolutePath());
    			imageField.setImage(image);
    			this.imageFileName=selectedFile.getName();
    		}
    				
    }
    
    
    
    public void saveImage(String imagePath, String folderPath) {
        byte[] content;
        try {
            content = Files.readAllBytes(Paths.get(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        
        String fileName = Paths.get(imagePath).getFileName().toString();

        Path folder = Paths.get(folderPath);

        if (!Files.exists(folder)) {
            try {
                Files.createDirectories(folder);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        Path filePath = folder.resolve(fileName);

        try {
            Files.write(filePath, content);
            System.out.println("Image saved successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving the image.");
        }
    }

}
