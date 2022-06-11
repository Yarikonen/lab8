package commands;

import collec_class.CollectionManager;
import collec_class.Route;
import server.Response;
import utils.WrongScriptException;


import java.io.*;

public class SaveCommand extends Command {
    private boolean another_script;
    private String file = "";

    public SaveCommand(Boolean another_script){
        this.another_script = another_script;
    }


    @Override
    public Response execute(CollectionManager<Route> collectionMan, BufferedReader buff) throws IOException {


        return(new Response(true,this));

    }








    @Override
    public String description() {
        return("сохраняю коллекцию в файл");

    }

    @Override
    public String getName() {
        return("save");
    }
}