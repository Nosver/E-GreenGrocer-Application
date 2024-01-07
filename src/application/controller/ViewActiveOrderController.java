package application.controller;

import java.time.LocalDateTime;

import application.model.Chart;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ViewActiveOrderController {

    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<Chart, String> State;

    @FXML
    private TableView<Chart> activeOrdersTable;

    @FXML
    private TableColumn<Chart, String> customerName;

    @FXML
    private TableColumn<Chart, LocalDateTime> orderDate;

    @FXML
    private TableColumn<Chart, Double> totalPrice;

    @FXML
    void backButtonClicked(MouseEvent event) {

    }
    
    @FXML
    public void initialize()
    {
    	
    }
}
