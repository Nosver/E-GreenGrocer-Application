module group {
    requires javafx.controls;
    requires javafx.fxml;

    opens group to javafx.fxml;
    exports group;
}
