module com.example.edadesktopapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;


    opens com.example.edadesktopapp to javafx.fxml;
    exports com.example.edadesktopapp;
}