module com.example.edadesktopapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires java.sql;
    requires com.google.gson;

    opens com.example.edadesktopapp to javafx.fxml,  com.google.gson;
    exports com.example.edadesktopapp;
}