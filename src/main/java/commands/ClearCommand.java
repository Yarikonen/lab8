package commands;

import collec_class.CollectionManager;
import collec_class.Route;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ClearCommand extends Command {
    private static final long serialVersionUID = 2L;
    private final String userName;
    public ClearCommand(Boolean fromAnotherScript, String userName){
        this.userName=userName;

    }
    @Override
    public Response execute(CollectionManager<Route> collectionMan, BufferedReader buff) throws IOException {
        return(new Response(true,this));
    }

    @Override
    public String description() {
        return("чисти");

    }

    @Override
    public String getName() {
        return("clear");
    }


}
