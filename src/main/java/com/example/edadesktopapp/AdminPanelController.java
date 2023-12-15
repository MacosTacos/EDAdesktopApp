package com.example.edadesktopapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminPanelController {

    @FXML
    private Button controlButton;

    @FXML
    private Button ordersButton;

    @FXML
    void openAdminView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage currentStage = (Stage) controlButton.getScene().getWindow();
            currentStage.setTitle("Список заказов");

            currentStage.setScene(scene);

            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openOrdersView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orders-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage currentStage = (Stage) controlButton.getScene().getWindow();
            currentStage.setTitle("Список заказов");

            currentStage.setScene(scene);

            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
