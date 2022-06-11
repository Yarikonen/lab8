package commands;

import collec_class.CollectionManager;
import collec_class.Route;
import server.Response;
import utils.WrongScriptException;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RemoveByIdCommand extends Command{
    private boolean anotherscript;
    private Integer id ;
    private final String userName;
    private static final long serialVersionUID = 4L;
    public RemoveByIdCommand(Boolean anotherscript,String userName,int id){
        this.anotherscript = anotherscript;
        this.userName =userName;
        this.id=id;

    }
    @Override
    public Response execute(CollectionManager<Route> collectionMan, BufferedReader buff) throws IOException {
        return(new Response(true,this));
    }

    @Override
    public String getName() {
        return("remove_by_id");
    }

    @Override
    public String description() {
        return("могу удалять элемент по его id");
    }


}
