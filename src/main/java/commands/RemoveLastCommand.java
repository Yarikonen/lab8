package commands;

import collec_class.CollectionManager;
import collec_class.Route;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;

public class RemoveLastCommand extends Command {
    private static final long serialVersionUID = 5L;
    @Override
    public Response execute(CollectionManager<Route> collectionMan, BufferedReader buff) throws IOException {
        if (serverMode) {
            if (collectionMan.getStorage().isEmpty()) {
                return (new Response(false, this, "already pusto"));
            } else {
                collectionMan.getStorage().pop();
            }
            return (new Response(true, this));
        }
        else{
            return(new Response(true,this));
        }
    }

    @Override
    public String description() {
        return("удалю послдений элемент");

    }

    @Override
    public String getName() {
        return("remove_last");
    }
}
