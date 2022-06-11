package com.example.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Locale loc = Locale.US;
        ResourceBundle rb = ResourceBundle.getBundle("locales.GuiLanguage",loc);
        System.out.println(rb.getLocale());

        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("loginStage.fxml"),rb);
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setResizable(false);
        stage.setTitle("Login!");
        stage.getIcons().add(new Image("https://img.ifunny.co/images/803eddf124ffda76b3a3c01804920eceb1060be133516f1ec0a6e28d686adca6_1.jpg"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String ... args) {
        launch();


    }

    }
