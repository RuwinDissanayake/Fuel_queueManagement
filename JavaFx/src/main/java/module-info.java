module com.example.sdiicw {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sdiicw to javafx.fxml;
    exports com.example.sdiicw;
}