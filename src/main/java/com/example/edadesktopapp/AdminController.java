package com.example.edadesktopapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import retrofit2.Call;
import retrofit2.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private TextField categoryNameRusField;

    @FXML
    private TextField categoryNameEngField;

    @FXML
    private Button addCategoryButton;

    @FXML
    private Button addFoodButton;

    @FXML
    private Button addInMenuButton;

    @FXML
    private Button addOrderButton;

    @FXML
    private ChoiceBox<GetFoodResponse> foodBox;

    @FXML
    private TextField foodCategoryField;

    @FXML
    private ListView<GetCategoriesResponse> categoriesListView;

    @FXML
    private ListView<GetFoodResponse> foodListView;

    @FXML
    private TextField foodNameField;

    @FXML
    private TextField foodPriceField;

    @FXML
    private TextField foodQuantityField;

    @FXML
    private ListView<OrderContents> orderListView;

    @FXML
    private ChoiceBox<GetCategoriesResponse> categoryBox;

    @FXML
    private TextField quantityField;

    @FXML
    private Button goBackButton;

    private ObservableList<OrderContents> products = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showFood();
        showCategories();
    }

    @FXML
    void addFood(ActionEvent event) {
        String product = foodBox.getSelectionModel().getSelectedItem().getName();
        String quantity = quantityField.getText();
        int id = foodBox.getSelectionModel().getSelectedItem().getId();
        OrderContents orderContents = new OrderContents(id, product, quantity);
        products.add(orderContents);
        orderListView.setItems(products);
    }

    @FXML
    void addInMenu(ActionEvent event) {
        String foodName = foodNameField.getText();
        double foodPrice = Double.parseDouble(foodPriceField.getText());
        int foodQuantity = Integer.parseInt(foodQuantityField.getText());
        int categoryId = categoryBox.getSelectionModel().getSelectedItem().getId();

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        AddFoodRequest addFoodRequest = new AddFoodRequest(foodName, foodQuantity, foodPrice, "", categoryId);
        Call<String> addFoodCall =  apiService.addFood(ApiClient.getToken(), addFoodRequest);
        addFoodCall.enqueue(new retrofit2.Callback<String>(){

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    System.out.println(response.body());
                }
                javafx.application.Platform.runLater(() -> showFood());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(t);
                javafx.application.Platform.runLater(() -> showFood());
            }
        });
    }

    @FXML
    void addOrder(ActionEvent event) {
        String ordersId = "";
        String ordersQuantities = "";
        for (OrderContents i : products){
            ordersId += i.getFoodId() + ";";
            ordersQuantities += i.getQuantity() + ";";
        }
        ordersId = ordersId.substring(0, ordersId.length() - 1);
        ordersQuantities = ordersQuantities.substring(0, ordersQuantities.length() - 1);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        AddOrderRequest addOrderRequest = new AddOrderRequest(ordersId, ordersQuantities);
        Call<String> addOrderCall = apiService.addOrder(ApiClient.getToken(), addOrderRequest);
        addOrderCall.enqueue(new retrofit2.Callback<String>(){

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    System.out.println(response.body());
                }
                javafx.application.Platform.runLater(() -> products.clear());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(t);
                javafx.application.Platform.runLater(() -> products.clear());
            }
        });



    }

    @FXML
    void goBack(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminPanel-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage currentStage = (Stage) goBackButton.getScene().getWindow();
            currentStage.setTitle("");

            currentStage.setScene(scene);

            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addCategory(ActionEvent event){
        String categoryRusName = categoryNameRusField.getText();
        String categoryEngName = categoryNameEngField.getText();
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest(categoryEngName, categoryRusName);
        Call<String> addCategoryCall = apiService.addCategory(ApiClient.getToken(), addCategoryRequest);
        addCategoryCall.enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    System.out.println(response.body());
                }
                javafx.application.Platform.runLater(() -> showCategories());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("gg");
                javafx.application.Platform.runLater(() -> showCategories());
            }
        });

    }





    private void showFood(){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<GetFoodResponse>> getFoodCall = apiService.getFood(ApiClient.getToken());
        getFoodCall.enqueue(new retrofit2.Callback<List<GetFoodResponse>>(){

            @Override
            public void onResponse(Call<List<GetFoodResponse>> call, Response<List<GetFoodResponse>> response) {
                if (response.isSuccessful()){
                    List<GetFoodResponse> getFoodResponseList = response.body();

                    javafx.application.Platform.runLater(() -> changeFoodListView(getFoodResponseList));
                    javafx.application.Platform.runLater(() -> setFoodBox(getFoodResponseList));


                }
            }

            @Override
            public void onFailure(Call<List<GetFoodResponse>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }



    private void showCategories(){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<GetCategoriesResponse>> getCategoriesCall = apiService.getCategories(ApiClient.getToken());
        getCategoriesCall.enqueue(new retrofit2.Callback<List<GetCategoriesResponse>>(){

            @Override
            public void onResponse(Call<List<GetCategoriesResponse>> call, Response<List<GetCategoriesResponse>> response) {
                if (response.isSuccessful()){
                    /*List<GetCategoriesResponse> categoriesList = response.body();
                    javafx.application.Platform.runLater(() -> {
                        categoryBox.getItems().setAll(categoriesList);
                        categoriesListView.getItems().setAll(categoriesList);

                    });*/
                }
                List<GetCategoriesResponse> categoriesList = response.body();
                javafx.application.Platform.runLater(() -> {
                    categoryBox.getItems().setAll(categoriesList);
                    categoriesListView.getItems().setAll(categoriesList);
                });
            }

            @Override
            public void onFailure(Call<List<GetCategoriesResponse>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    private void setFoodBox(List<GetFoodResponse> list){
        ObservableList<GetFoodResponse> observableList = FXCollections.observableArrayList(list);

        foodBox.setItems(observableList);
    }

    private void changeFoodListView(List<GetFoodResponse> list) {

        ObservableList<GetFoodResponse> observableList = FXCollections.observableArrayList(list);

        foodListView.setItems(observableList);
    }

}
