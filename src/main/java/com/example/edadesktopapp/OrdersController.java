package com.example.edadesktopapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Response;

import java.net.URL;
import java.sql.SQLOutput;
import java.util.List;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {

    @FXML
    private Button orderIsReadyButton;

    @FXML
    private ListView<GetOrdersResponse> ordersListView;

    @FXML
    private Button goBackButton;


    @FXML
    void changeOrderStatus(ActionEvent event) {
        int selectedId = ordersListView.getSelectionModel().getSelectedIndex();
        GetOrdersResponse getOrdersResponse = ordersListView.getItems().get(selectedId);
        ChangeOrderStatusRequest changeOrderStatusRequest = new ChangeOrderStatusRequest(getOrdersResponse.getId(), 2);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<String> call = apiService.changeStatus(ApiClient.getToken(), changeOrderStatusRequest);
        call.enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    System.out.println("sucsessfuly");
                    ordersListView.getItems().remove(selectedId);
                }
                javafx.application.Platform.runLater(() -> showOrders());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("gg");
                javafx.application.Platform.runLater(() -> showOrders());
            }

        });
    }

    @FXML
    void goBack(ActionEvent event){
        String role = HelloController.getROLE();
        switch (role){
            case "ADMIN" -> {
                openAdminPanel();
            }
            case "STAFF" -> {
                openStartWindow();
            }
        }
    }

    private void changeListView(List<GetOrdersResponse> list) {

        ObservableList<GetOrdersResponse> observableList = FXCollections.observableArrayList(list);
        ordersListView.setItems(observableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showOrders();

    }

    private void showOrders(){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        //GetOrdersResponse getOrdersResponse = new GetOrdersResponse(1, 1);
        Call<List<GetOrdersResponse>> callOrders = apiService.getOrders(ApiClient.getToken());
        callOrders.enqueue(new retrofit2.Callback<List<GetOrdersResponse>>() {
            @Override
            public void onResponse(Call<List<GetOrdersResponse>> call, retrofit2.Response<List<GetOrdersResponse>> response) {
                if (response.isSuccessful()) {
                    List<GetOrdersResponse> ordersList = response.body();

                    //System.out.println("Orders: " + getOrdersResponse.getOrderItemEntityList());
                    javafx.application.Platform.runLater(() -> changeListView(ordersList));
                } else {
                    System.out.println("Error:1" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<GetOrdersResponse>> call, Throwable throwable) {
                System.out.println("Error:2" + throwable.getMessage());
            }
        });
    }

    private void openAdminPanel(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminPanel-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage currentStage = (Stage) goBackButton.getScene().getWindow();
            currentStage.setTitle("Список заказов");

            currentStage.setScene(scene);

            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openStartWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage currentStage = (Stage) goBackButton.getScene().getWindow();
            currentStage.setTitle("Список заказов");

            currentStage.setScene(scene);

            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}