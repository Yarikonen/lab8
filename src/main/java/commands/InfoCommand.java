package commands;

import collec_class.CollectionManager;
import collec_class.Route;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

public class InfoCommand extends Command {


    @Override
    public Response execute(CollectionManager<Route> collectionMan, BufferedReader buff) throws IOException {
        if (serverMode) {
            Stack<Route> b = collectionMan.getStorage();
            System.out.println((b.getClass().getName() + " " + collectionMan.getCreationDate().toString()));
            return(new Response(true,this));
        }
        else{
            return new Response(false,this);
        }

    }

    @Override
    public String description() {
        return("информация о коллекции");

    }

    @Override
    public String getName() {
        return("info");
    }

}
