package commands;

import collec_class.CollectionManager;
import collec_class.Route;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;

public class SumOfDistanceCommand extends Command {
    private static final long serialVersionUID = 8L;
    @Override
    public Response execute(CollectionManager<Route> collectionMan, BufferedReader buff) throws IOException {
        if (serverMode) {
            return (new Response(true, this, collectionMan.sum_distance().toString()));
        }
        return(new Response(true,this));
    }

    @Override
    public String description() {
        return("суммирую дистанции");

    }

    @Override
    public String getName() {
        return("sum_of_distance");
    }
}
