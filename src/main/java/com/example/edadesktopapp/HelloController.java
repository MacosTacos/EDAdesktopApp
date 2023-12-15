package com.example.edadesktopapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import retrofit2.Call;

public class HelloController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    private static String ROLE;

    @FXML
    void signIn(ActionEvent event) { ApiService apiService = ApiClient.getClient().create(ApiService.class);
        UserEntityRequest userEntityRequest = new UserEntityRequest(loginField.getText(), passwordField.getText());
        Call<TokenResponse> callLogin = apiService.loginUser(userEntityRequest);

        callLogin.enqueue(new retrofit2.Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, retrofit2.Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    TokenResponse tokenResponse = response.body();
                    ApiClient.setToken(tokenResponse.getToken());
                    System.out.println("Token: " + tokenResponse.getToken());
                    String role = tokenResponse.getRole();
                    ROLE = role;
                    switch (role){
                        case "STAFF" -> {
                            javafx.application.Platform.runLater(() -> openStaffWindow());
                        }
                        case "ADMIN" -> {
                            javafx.application.Platform.runLater(() -> openAdminPanel());
                        }
                    }

                } else {
                    System.out.println("Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }
        });
    }

    void openAdminPanel(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminPanel-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage currentStage = (Stage) signInButton.getScene().getWindow();
            currentStage.setTitle("Список заказов");

            currentStage.setScene(scene);

            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void openStaffWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orders-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage currentStage = (Stage) signInButton.getScene().getWindow();
            currentStage.setTitle("Список заказов");

            currentStage.setScene(scene);

            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getROLE() {
        return ROLE;
    }
}