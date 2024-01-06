package application.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import application.DatabaseAdapter;
import application.model.Chart;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CourrierHomeScreenController {

    @FXML
    private TableColumn<Chart, String> CustomerAdressColumn;

    @FXML
    private TableColumn<Chart, Integer> userIdColumn; // getUserID
    
    @FXML
    private TableColumn<Chart, String> StateColumn;

    @FXML
    private TableColumn<Chart, LocalDateTime> DateColumn;

    @FXML
    private TableColumn<Chart, Double> TotalPriceColumn;

    @FXML
    private TableView<Chart> orderTable;
    
    private DatabaseAdapter db = new DatabaseAdapter();
    
    
    private void loadPurchasedCharts() throws SQLException {
        List<Chart> purchasedCharts = db.getPurchasedCharts();

        orderTable.getItems().setAll(purchasedCharts);
    }

    
    
    @FXML
    public void initialize() {
        
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        TotalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        StateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            loadPurchasedCharts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
}
