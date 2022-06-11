package commands;

import collec_class.CollectionManager;
import collec_class.Route;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {
    protected Boolean serverMode = false;
    private String userName="";



    private Boolean fromAnotherScript;

    public abstract Response execute(CollectionManager<Route> collectionMan, BufferedReader buff) throws IOException;
    public abstract String description();
    public String getName(){
        return(this.getClass().getName());
    }
    public Command(){
    }
    public void changeservermode(Boolean b){
        serverMode =b;
    }
    public Command(Boolean fromAnotherScript,String userName){
        this.fromAnotherScript=fromAnotherScript;
        this.userName=userName;
    }
    public Command(Boolean fromAnotherScript){
        this.fromAnotherScript=fromAnotherScript;
    }
    public String getUserName(){
        return(userName);
    }
    public Boolean getFromAnotherScript() {
        return fromAnotherScript;
    }



}
