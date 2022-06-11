package commands;

import collec_class.CollectionManager;
import collec_class.Route;
import server.Response;
import utils.WrongScriptException;

import java.io.*;
import java.sql.Connection;
import java.util.Stack;

public class ExecuteScriptCommand extends Command  {
    private CollectionManager<Route> target;
    private Stack<String> scripts = new Stack<>();
    private Stack<Response> responses = new Stack<>();
    private String script;
    private String scriptNext;
    private transient Connection connection;
    private static final long serialVersionUID = 999L;
    private String userName;

    public ExecuteScriptCommand(boolean fromAnotherScript, String userName){
        this.userName=userName;

    }
    @Override
    public Response execute(CollectionManager<Route> collectionMan, BufferedReader buff) throws IOException {
        scripts = new Stack<>();
        responses = new Stack<>();
        target = collectionMan;
        buff.reset();
        String[] content = buff.readLine().split(" ");
        if (content.length == 2) {
            script = content[1];
        } else {
            System.out.println("Введите название скрипта, вы забыли");
            script = buff.readLine();
        }
        return(new Response(true,this));
    }

    @Override
    public String description() {
        return("я умею запускать скрипт");

    }

    @Override
    public String getName() {
        return("execute_script");
    }





}