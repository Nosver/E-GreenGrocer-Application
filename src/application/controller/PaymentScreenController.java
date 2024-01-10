package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import application.SceneSwitch;
import javafx.fxml.FXML;
public class PaymentScreenController {

    @FXML
    private TextField CardNumber;

    @FXML
    private TextField CvcNumber;

    @FXML
    private TextField Date;

    @FXML
    private TextField NameText;

    @FXML
    private Button PayButton;

    @FXML
    void PayButtonMouseClicked(MouseEvent event) {
        String enteredCardNumber = CardNumber.getText();
       
        String validCardNumber1 = "1234567812345670";
        String validCardNumber2 = "9876543298765432";
        
        if (enteredCardNumber.equals(validCardNumber1) || enteredCardNumber.equals(validCardNumber2)) {
            showPaymentSuccessAlert();
        } else {
            showPaymentFailureAlert();
        }
    }

    private void showPaymentSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PAYMENT SUCCESSFUL!");
        alert.setHeaderText(null);
        alert.setContentText("Payment completed successfully!");
        alert.showAndWait();
    }

    private void showPaymentFailureAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("PAYMNET FAİLED");
        alert.setHeaderText(null);
        alert.setContentText("Payment failed. Please enter a valid credit card number.");
        alert.showAndWait();
    }
}
Bu 