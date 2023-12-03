package com.example.edadesktopapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import retrofit2.Call;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {

    @FXML
    private Button orderIsReadyButton;

    @FXML
    private ListView<GetOrdersResponse> ordersListView;

    @FXML
    void changeOrderStatus(ActionEvent event) {

    }

    private void changeListView(List<GetOrdersResponse> list){

        ObservableList<GetOrdersResponse> observableList = FXCollections.observableArrayList(list);
        ordersListView.setItems(observableList);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
                    System.out.println("Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<GetOrdersResponse>> call, Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }
        });

    }
}
