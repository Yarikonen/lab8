package com.example.client;

import collec_class.CollectionManager;
import collec_class.Route;
import custom.components.ValidTextField;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import server.Response;
import server.ResponsesHandler;
import server.User;
import utils.ObservableResourceFactory;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    ResourceBundle rb;
    @FXML
    private Scene scene;
    @FXML
    private ValidTextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private ValidTextField abc;
    @FXML private BorderPane bp;
    private Stage stage;
    public static DatagramSocket ds;
    ObservableResourceFactory observableResourceFactory = ObservableResourceFactory.getInstance();
    public static CollectionManager<Route> collectionMan;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.rb=resourceBundle;
        observableResourceFactory.setResources(rb);
        userNameLabel.textProperty().bind(observableResourceFactory.getStringBinding("username"));
        passwordLabel.textProperty().bind(observableResourceFactory.getStringBinding("password"));
        loginButton.textProperty().bind(observableResourceFactory.getStringBinding("log in"));



        userName.setValid((input-> !input.isEmpty()));
        loginButton.disableProperty().bind(userName.isValid().not());

    }
    @FXML protected void languageChangerButton() throws IOException {
        Stage stage2 = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("languageChange.fxml"),rb);
        Scene scene = new Scene(fxmlLoader.load(), 191, 138);
        stage2.setResizable(false);
        stage2.setTitle(rb.getString("changeLanguage"));
        stage2.setScene(scene);
        stage2.showAndWait();
        this.rb=LanguageChange.rb;


    }
    @FXML
    protected void LoginButtonClick() {
        clientEx(userName.getText(),password.getText());
    }
    private void clientEx(String password, String userName){
        stage= (Stage) (bp.getScene().getWindow());
        try {
            InetAddress host;
            int port;
            ds = new DatagramSocket();
            ds.setSoTimeout(50000);
            host = InetAddress.getLocalHost();
            port = 6780;
            CollectionManager<Route> collMan = new CollectionManager<>();
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            ResponsesHandler<Response> responsesHandler=new ResponsesHandler<>(host,port,ds);
            ResponsesHandler<String> messageHandler=new ResponsesHandler<>(host,port,ds);
            ResponsesHandler<CollectionManager<Route>> collectionHandler = new ResponsesHandler<>(host,port,ds);
            User user = new User(userName,password);
            MainStageController.userName = user.getUserName();

            String respText=user.login(messageHandler,responsesHandler);
            collectionMan = collectionHandler.get();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("KEKW");
            alert.setHeaderText(rb.getString(respText));

            if (respText.equals("big login")){
                alert.showAndWait();
                FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("mainStage.fxml"),rb);
                Scene scene = new Scene(fxmlLoader.load(), 859.2, 563.2);
                stage.setResizable(false);
                stage.setTitle("Hello!");
                stage.setScene(scene);
                stage.show();
            }
            else{
                alert.show();
            }



//            CommandMan man = new CommandMan(
//                    new HentaiCommand()
//                    ,new ExitCommand()
//                    ,new ShowCommand()
//                    ,new ClearCommand(false,user.getUserName())
//                    ,new RemoveByIdCommand(false, user.getUserName())
//                    ,new InfoCommand()
//                    ,new AddCommand(false,user.getUserName())
//                    ,new HelpCommand()
//                    ,new UpdateByIDCommand(false, user.getUserName())
//                    ,new ExecuteScriptCommand(false,user.getUserName())
//                    ,new RemoveLastCommand()
//                    ,new RemoveLoverCommand(false, user.getUserName())
//                    ,new ReorderCommand()
//                    ,new RemoveByDistanceCommand(false, user.getUserName())
//                    ,new SumOfDistanceCommand()
//                    ,new FilterNameCommand(false));
//            boolean booling = true;
//
//
//            do {
//                try {
//                    PrintStream originalStream = System.out;
//                    System.setOut(originalStream);
//                    buff.mark(8192);
//                    String aa = buff.readLine();
//                    Response resp = man.command_exec(aa.split(" ")[0], collMan, buff, false);
//                    man.changeservermode(aa.split(" ")[0], true);
//                    responsesHandler.send(resp);
//                    System.out.println(responsesHandler.get().getMessage());
//                    man.changeservermode(aa.split(" ")[0], false);
//
//                } catch (NullPointerException excp) {
//                    System.out.println("нет такой команды//вы принудительно вышли");
//
//                }   catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//
//            } while (booling);

        }catch (SocketTimeoutException exp) {
            System.out.println("сервер закрыт.....");
            LoginApplication.main();

        }
        catch(SocketException | UnknownHostException exp2){
            System.out.println("ТЫ ЛОХ");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}