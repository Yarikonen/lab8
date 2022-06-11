package commands;

import collec_class.CollectionManager;
import collec_class.Route;
import server.Response;
import utils.WrongScriptException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;


public  class CommandMan {


    private Map<String, Command> commandMap = new HashMap<>();
    private static PrintStream originalStream;
    {originalStream = System.out;}
    public CommandMan(Command ... commands){

        for (Command i: commands
        ) {
            commandMap.put(i.getName(),i);
        }

    }
    public void changeservermode(String a,boolean s){
        commandMap.get(a).changeservermode(s);
    }
    public Response command_exec(String a, CollectionManager<Route> target, BufferedReader buff, Boolean fromanotherscript) throws IOException, NullPointerException {
        if (fromanotherscript){
            PrintStream dummyStream = new PrintStream(new OutputStream(){
                public void write(int b) {
                }
            });
            System.setOut(dummyStream);
        }
        try {
            Response resp = commandMap.get(a).execute(target, buff);
            System.setOut(originalStream);
            return(resp);
        }catch(WrongScriptException ex){
            System.setOut(originalStream);
            throw new WrongScriptException();

        }
        catch(NullPointerException ex){
            throw new NullPointerException();
        }

    }
    public void upgComMap(Command ... commands){
        for (Command i: commands
        ) {
            commandMap.put(i.getName(),i);
        }

    }
    public Command commandgetter(String a){
        return(commandMap.get(a));
    }


}
