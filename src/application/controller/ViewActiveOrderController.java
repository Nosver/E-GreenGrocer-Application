package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ViewActiveOrderController {

    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<?, ?> State;

    @FXML
    private TableView<?> activeOrdersTable;

    @FXML
    private TableColumn<?, ?> customerName;

    @FXML
    private TableColumn<?, ?> orderDate;

    @FXML
    private TableColumn<?, ?> totalPrice;

    @FXML
    void backButtonClicked(MouseEvent event) {

    }

}
