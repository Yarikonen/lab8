package commands;

import collec_class.*;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

public class ShowCommand extends Command {
    Stack<Route> target = new Stack<>();

    @Override
    public Response execute(CollectionManager<Route> collectionMan, BufferedReader buff) throws IOException {
        if (serverMode) {
            this.target = collectionMan.getStorage();
            return(new Response(true,this,this.infor()));

        }
        else{
            return(new Response(true,this));
        }

    }

    @Override
    public String description() {
        return("вывожу че в коллекции");

    }

    @Override
    public String getName() {
        return("show");
    }
    public String infor(){
        String a ="";
        for (Route route: target
             ) {
            a+=(route.toString()) +"\n";
        }
        return(a);
    }

}
