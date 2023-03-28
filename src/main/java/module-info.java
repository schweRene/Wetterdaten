module com.example.wetterdaten {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.wetterdaten to javafx.fxml;
    exports com.example.wetterdaten;
}