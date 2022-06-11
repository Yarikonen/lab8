package com.example.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import utils.ObservableResourceFactory;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageChange implements Initializable {
    public static ResourceBundle rb;
    @FXML
    private Button lButton;
    @FXML
    private ChoiceBox choiceBox;
    private ObservableResourceFactory observableResourceFactory;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.rb = resourceBundle;
        observableResourceFactory=ObservableResourceFactory.getInstance();
        ObservableList<String> langs = FXCollections.observableArrayList("EN", "RU","NL");
        observableResourceFactory.setResources(rb);
        lButton.textProperty().bind(observableResourceFactory.getStringBinding("changeLanguage"));
        choiceBox.setItems(langs);
        choiceBox.setValue("EN");

    }
    @FXML
    public void changeLanguageButton(){
        if (choiceBox.getValue().equals("RU")){
            Locale loc = new Locale("ru", "RU");
            this.rb = ResourceBundle.getBundle("locales.GuiLanguage",loc);
            observableResourceFactory.setResources(rb);
        }
        if (choiceBox.getValue().equals("EN")){
            Locale loc = Locale.US;
            this.rb = ResourceBundle.getBundle("locales.GuiLanguage",loc);
            observableResourceFactory.setResources(rb);

        }
        if (choiceBox.getValue().equals("NL")){
            Locale loc = new Locale("nl", "NL");
            this.rb = ResourceBundle.getBundle("locales.GuiLanguage",loc);
            observableResourceFactory.setResources(rb);

        }


    }

}
