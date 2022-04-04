module com.example.lab6 {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.swing;
    requires javafx.base;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens Controlers to javafx.fxml;
    opens GUI to javafx.fxml;
    exports GUI;
    exports Controlers;
}