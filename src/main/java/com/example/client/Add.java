package com.example.client;

import collec_class.Coordinates;
import collec_class.Location;
import collec_class.Route;
import custom.components.ValidTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Add implements Initializable {
    @FXML
    Button button;
    @FXML
    ValidTextField coordinateX;
    @FXML
    ValidTextField coordinateY;
    @FXML
    ValidTextField xFrom;
    @FXML
    ValidTextField yFrom;
    @FXML
    ValidTextField xTo;
    @FXML
    ValidTextField yTo;
    @FXML
    ValidTextField distance;
    @FXML
    ValidTextField routeName;
    ResourceBundle rb;
    public static Route route;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.rb = resourceBundle;
        coordinateX.setValid(s -> {
            try{
                Long.parseLong(s);
                return(true);

            }catch(NumberFormatException exp){
                return(false);
            }
        });
        coordinateY.setValid(s -> {
            try{
                Long.parseLong(s);
                return(true);

            }catch(NumberFormatException exp){
                return(false);
            }
        });
        xTo.setValid(s -> {
            try{
                Long.parseLong(s);
                return(true);

            }catch(NumberFormatException exp){
                return(false);
            }
        });
        yTo.setValid(s -> {
            try{
                Long.parseLong(s);
                return(true);

            }catch(NumberFormatException exp){
                return(false);
            }
        });
        xFrom.setValid(s -> {
            try{
                Long.parseLong(s);
                return(true);

            }catch(NumberFormatException exp){
                return(false);
            }
        });
        yFrom.setValid(s -> {
            try{
                Long.parseLong(s);
                return(true);

            }catch(NumberFormatException exp){
                return(false);
            }
        });
        distance.setValid(s -> {
            try{
                Long.parseLong(s);
                return(true);

            }catch(NumberFormatException exp){
                return(false);
            }
        });
        routeName.setValid(s -> !s.isEmpty());
        button.disableProperty().bind(coordinateX.isValid().not().or(coordinateY.isValid().not()).or(xTo.isValid().not()).or(yTo.isValid().not()).or(distance.isValid().not()).or(routeName.isValid().not()));


    }
    @FXML
    private void add(){
        route = new Route();
        route.setTo(new Location(Double.parseDouble(xTo.getText()),Long.parseLong(yTo.getText()),""));
        route.setFrom(new Location(Double.parseDouble(xFrom.getText()),Long.parseLong(yFrom.getText()),""));
        route.setName(routeName.getText());
        route.setDistance(Long.parseLong(distance.getText()));
        route.setCoordinates(new Coordinates(Long.parseLong(coordinateX.getText()),Long.parseLong(coordinateY.getText())));
        route.setUserName(MainStageController.userName);
        Stage stage= (Stage) (yTo.getScene().getWindow());
        stage.close();

    }

}
