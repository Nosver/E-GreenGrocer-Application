package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


// Main java
public class Main extends Application {

	/*
	 * 
	 -giriş ekranı
	  		login
	  		sign in
	--kurye ekranı //yiğit
				sipariş görme
				sipariş teslimi
	-owner ekranı //Masis
				kurye ekleme
				stok ekleme
				product ekleme //Done
				product silme
				kurye silme
				stok silme
				view all orders (all types)
				
		- chart screen //güney
				-charttan ekle çıkar
				-ödeme ekranına geç butonu 
				(if addres varsa ) 
						adressi göster ve update sor
				else
					adress sor
					
	--password visibility
	
	-- product ekranı // kemal doğukan 

	 
	 * */

	@Override
	public void start(Stage primaryStage) {
	    try {
	        // Load Login Screen FXML file
	    	Parent root=FXMLLoader.load(getClass().getResource("newProductScreen.fxml"));
			Scene scene= new Scene(root);

	        // Set the main FXML file as the scene
	       
			
			

	        // Set the stage title and scene
	        primaryStage.setTitle("Main Screen");
	        primaryStage.setScene(scene);
	        //primaryStage.setResizable(false);
	        // Show the main screen
	        primaryStage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public static void main(String[] args) {
		
		launch(args);
		
		
		
	}
}
