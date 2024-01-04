package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UpdateUserInfoController {

    @FXML
    private TextArea addressField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameUpdateField;

    @FXML
    private TextField passwordUpdateField;

    @FXML
    private Button updateButton;

    @FXML
    void updateButtonClicked(MouseEvent event) {
    		System.out.println("clicked");
    }

}
