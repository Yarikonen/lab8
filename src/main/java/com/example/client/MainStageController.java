package com.example.client;

import collec_class.*;
import commands.*;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import server.Response;
import server.ResponsesHandler;
import utils.ObservableResourceFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.concurrent.ExecutionException;

public class MainStageController implements Initializable {
    private DatagramSocket ds;
    private ResourceBundle rb;
    private CollectionManager<Route> collectionManager;
    public static String userName;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private MenuButton menuView;
    @FXML
    private MenuButton menuEdit;

    @FXML
    private MenuItem add;
    @FXML
    private MenuItem remove;
    @FXML
    private MenuItem clear;
    @FXML
    private AnchorPane mapPane;
    @FXML
    private MenuItem removeByDistance;
    @FXML
    private MenuItem removeLast;
    @FXML
    private MenuItem removeLover;
    @FXML
    private MenuItem updateC;

    @FXML
    private TableView<Route> table;
    @FXML
    private TableColumn<Route, String> ID;
    @FXML
    private TableColumn<Route, String> routeName;
    @FXML
    private TableColumn<Route, Coordinates> coordinates;
    @FXML
    private TableColumn<Route, String> xCoordinate;
    @FXML
    private TableColumn<Route, String> yCoordinate;
    @FXML
    private TableColumn<Route, String> creationDate;
    @FXML
    private TableColumn<Route, Locationto> locationTo;
    @FXML
    private TableColumn<Route, Location> locationFrom;
    @FXML
    private TableColumn<Route, String> xTo;
    @FXML
    private TableColumn<Route, String> yTo;
    @FXML
    private TableColumn<Route, String> xFrom;
    @FXML
    private TableColumn<Route, String> yFrom;
    @FXML
    private TableColumn<Route, String> distance;
    @FXML
    private TableColumn<Route, String> creator;
    @FXML
    private MenuItem languageChanger;

    @FXML
    private Tab table2;
    @FXML
    private Tab map;
    private final HashMap<Shape, Long> shapeMap = new HashMap<>();
    private ObservableResourceFactory observableResourceFactory = ObservableResourceFactory.getInstance();
    private InetAddress host;
    private int port;
    ResponsesHandler<Response> responsesHandler;
    ResponsesHandler<String> messageHandler;
    ResponsesHandler<CollectionManager<Route>> collectionHandler;
    Thread requestCollection;
    Boolean requestable=true;
    Task<Response> task;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        port = 6780;
        this.ds = LoginController.ds;
        this.rb = resourceBundle;
        this.collectionManager = LoginController.collectionMan;
        observableResourceFactory.setResources(rb);
        responsesHandler = new ResponsesHandler<>(host, port, ds);
        messageHandler = new ResponsesHandler<>(host, port, ds);
        collectionHandler = new ResponsesHandler<>(host, port, ds);
        requestCollection = new Thread(() -> {

            while (true) {
                if (requestable) {
                    try {
                        requestCollection();
                    } catch (UnknownHostException e) {

                    } catch (IOException e) {

                    } catch (ClassNotFoundException e) {

                    } catch (InterruptedException e) {

                    }
                }
                else{
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
        requestCollection.start();
        this.cellFabricSetter();
        routeName.textProperty().bind(observableResourceFactory.getStringBinding("routeName"));
        coordinates.textProperty().bind(observableResourceFactory.getStringBinding("Coordinates"));
        creationDate.textProperty().bind(observableResourceFactory.getStringBinding("creationDate"));
        locationFrom.textProperty().bind(observableResourceFactory.getStringBinding("locationFrom"));
        locationTo.textProperty().bind(observableResourceFactory.getStringBinding("locationTo"));
        distance.textProperty().bind(observableResourceFactory.getStringBinding("distance"));
        creator.textProperty().bind(observableResourceFactory.getStringBinding("creator"));
        menuView.textProperty().bind(observableResourceFactory.getStringBinding("view"));
        clear.textProperty().bind(observableResourceFactory.getStringBinding("clear"));
        remove.textProperty().bind(observableResourceFactory.getStringBinding("remove"));
        removeByDistance.textProperty().bind(observableResourceFactory.getStringBinding("removebyDistance"));
        removeLast.textProperty().bind(observableResourceFactory.getStringBinding("removeLast"));
        removeLover.textProperty().bind(observableResourceFactory.getStringBinding("removeLover"));
        updateC.textProperty().bind(observableResourceFactory.getStringBinding("updateC"));
        table2.textProperty().bind(observableResourceFactory.getStringBinding("Table"));
        map.textProperty().bind(observableResourceFactory.getStringBinding("Map"));
        add.textProperty().bind(observableResourceFactory.getStringBinding("add"));
        languageChanger.textProperty().bind(observableResourceFactory.getStringBinding("changeLanguage"));
        menuEdit.textProperty().bind(observableResourceFactory.getStringBinding("editMenu"));

        for (Route r : collectionManager.getStorage()
        ) {
            table.getItems().add(r);

        }
    }
    private Task<Response> taskGen(){
        return(new Task<Response>() {
            Response response;
            @Override
            protected Response call() throws Exception {
                try {
                    response = responsesHandler.get();
                    requestable=true;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Platform.runLater(()->{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText((response.getMessage().split(" ")[0]+" " + rb.getString(response.getMessage().split(" ")[1])));
                    alert.setHeaderText("Cool");
                    alert.show();
                });

                requestable=true;
                return response;
            }
        });
    }
    public void draw(){
        shapeMap.clear();
        for (Route route : collectionManager.getStorage()) {
            Color color = Color.color(Math.random(),Math.random(),Math.random());

            Shape circle = new Circle(15,color);
            circle.setLayoutX(mapPane.widthProperty().subtract(0).getValue() / 4 + route.getFrom().getX()/40  );
            circle.setLayoutY(mapPane.widthProperty().subtract(0).getValue() / 4 + route.getTo().getY()/40);


            shapeMap.put(circle, (long) route.getID());
            circle.setStroke(javafx.scene.paint.Color.BLACK);
            mapPane.getChildren().addAll(circle);

        }

    }

    @FXML
    private void changeLanguage() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LanguageChange.fxml"), rb);
        Scene scene = new Scene(fxmlLoader.load(), 191, 138);
        stage.setResizable(false);
        stage.setTitle(rb.getString("changeLanguage"));
        stage.setScene(scene);
        stage.showAndWait();
        this.cellFabricSetter();

    }
    @FXML
    private void removeLover() throws IOException,ClassNotFoundException{
        requestable=false;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add.fxml"), rb);
        Scene scene = new Scene(fxmlLoader.load(), 340, 454);
        stage.setResizable(false);
        stage.setTitle(rb.getString("removeLover"));
        stage.setScene(scene);
        stage.showAndWait();
        RemoveLoverCommand removeLoverCommand = new RemoveLoverCommand(true,userName, Add.route);
        responsesHandler.send(new Response(true,removeLoverCommand,""));
        Thread th = new Thread(taskGen());
        th.start();
    }
    @FXML
    private void removeByDistance() throws IOException, ClassNotFoundException {
        requestable=false;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("distance.fxml"), rb);
        Scene scene = new Scene(fxmlLoader.load(), 182, 94);
        stage.setResizable(false);
        stage.setTitle(rb.getString("removebyDistance"));
        stage.setScene(scene);
        stage.showAndWait();
        RemoveByDistanceCommand removeByDistanceCommand = new RemoveByDistanceCommand(true,userName,Distance.distance);
        responsesHandler.send(new Response(true,removeByDistanceCommand,""));
        Thread th = new Thread(taskGen());
        th.start();


    }
    @FXML
    private void helpCommand() {

    }
    @FXML
    private void updateCommand() throws IOException, ClassNotFoundException {
        requestable=false;

        Route route = (table.getSelectionModel().getSelectedItems().get(0));
        if (!route.getUserName().equals(userName)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Нет доступа");
            alert.setHeaderText("Ошибка");
            alert.showAndWait();}
        else {
            int id = route.getID();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add.fxml"), rb);
            Scene scene = new Scene(fxmlLoader.load(), 340, 454);
            stage.setResizable(false);
            stage.setTitle(rb.getString("add")); //TODO RENAME
            stage.setScene(scene);
            stage.showAndWait();
            route = Add.route;
            route.setId(id);
            UpdateByIDCommand updateByIDCommand = new UpdateByIDCommand(false, userName, route);
            responsesHandler.send(new Response(true, updateByIDCommand));
            Thread th = new Thread(taskGen());
            th.start();
        }


    }
    @FXML
    private void removeLast() throws IOException, ClassNotFoundException {
        requestable=false;
        RemoveLastCommand removeCommand = new RemoveLastCommand();
        responsesHandler.send(new Response(true, removeCommand));
        Thread th = new Thread(taskGen());
        th.start();

    }

    @FXML
    private void remove() throws IOException, ClassNotFoundException {
        requestable=false;
        Route route = (table.getSelectionModel().getSelectedItems().get(0));
        if (!route.getUserName().equals(userName)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Нет доступа");
            alert.setHeaderText("Ошибка");
            alert.showAndWait();
        } else {
            RemoveByIdCommand removeByIdCommand = new RemoveByIdCommand(false, userName, route.getID());
            responsesHandler.send(new Response(true, removeByIdCommand));
            Thread th = new Thread(taskGen());
            th.start();

        }



    }

    private void requestCollection() throws IOException, ClassNotFoundException, InterruptedException {

        responsesHandler.send(new Response(null, null, "collRequest"));
        this.collectionManager=collectionHandler.get();
        Stack<Route> routes = collectionManager.getStorage();
        ObservableList<Route> routes2 = FXCollections.observableArrayList(new ArrayList<>(routes));
        table.setItems(routes2);
        table.refresh();

        Thread.sleep(300);
    }

    public void cellFabricSetter() {
        ID.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(rb.getLocale()).format(cellData.getValue().getID())));
        routeName.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().get_Name()));
        xCoordinate.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(rb.getLocale()).format(cellData.getValue().getCoordinates().getX())));
        yCoordinate.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(rb.getLocale()).format(cellData.getValue().getCoordinates().getY())));
        creationDate.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(rb.getLocale()).format(cellData.getValue().getCreationDate())));
        xFrom.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(rb.getLocale()).format(cellData.getValue().getFrom().getX())));
        yFrom.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(rb.getLocale()).format(cellData.getValue().getFrom().getY())));
        xTo.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(rb.getLocale()).format(cellData.getValue().getTo().getX())));
        yTo.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(rb.getLocale()).format(cellData.getValue().getTo().getY())));
        distance.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(rb.getLocale()).format(cellData.getValue().getDistance())));
        creator.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getUserName()));
    }

    @FXML
    private void add() throws IOException, InterruptedException, ClassNotFoundException, ExecutionException {
        requestable=false;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add.fxml"), rb);
        Scene scene = new Scene(fxmlLoader.load(), 340, 454);
        stage.setResizable(false);
        stage.setTitle(rb.getString("add"));
        stage.setScene(scene);
        stage.showAndWait();
        AddCommand addCommand = new AddCommand(true, userName, Add.route);
        responsesHandler.send(new Response(true, addCommand));
        Thread th = new Thread(taskGen());
        th.start();





    }





    @FXML
    private void clear() throws IOException, ClassNotFoundException {
        requestable=false;
        ClearCommand clearCommand = new ClearCommand(true,userName);
        responsesHandler.send(new Response(true, clearCommand));
        Thread th = new Thread(taskGen());
        th.start();

    }

}
