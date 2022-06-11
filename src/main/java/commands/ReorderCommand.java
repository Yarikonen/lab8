package commands;

import collec_class.CollectionManager;
import collec_class.Route;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;

public class ReorderCommand extends Command {
    private static final long serialVersionUID = 7L;
    @Override
    public Response execute(CollectionManager<Route> collectionMan, BufferedReader buff) throws IOException {
        if (serverMode){
            collectionMan.reorder();
            return(new Response(true,this));
        }
        else{
            return(new Response(true,this));
        }

    }

    @Override
    public String description() {
        return("переворачиваю коллекцию");

    }

    @Override
    public String getName() {
        return("reorder");
    }
}
