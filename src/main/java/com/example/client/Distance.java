package com.example.client;

import custom.components.ValidTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Distance implements Initializable {
    public static Long distance;
    ResourceBundle resourceBundle;
    @FXML
    ValidTextField distanceField;
    @FXML
    Button button;
    private void getDistance(){
        this.distance = Long.parseLong(distanceField.getText());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        distanceField.setValid(s -> {
            try{
                Long.parseLong(s);
                return(true);

            }catch(NumberFormatException exp){
                return(false);
            }
        });
        button.disableProperty().bind(distanceField.isValid().not());
        this.resourceBundle=resourceBundle;

    }
}
