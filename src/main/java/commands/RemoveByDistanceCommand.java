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

public class RemoveByDistanceCommand extends Command {
    boolean another_script;
    private Long distance = 0L;
    private static final long serialVersionUID = 3L;
    private final String userName;
    public RemoveByDistanceCommand(boolean another_script, String userName,Long distance){
        this.another_script = another_script;
        this.userName=userName;
        this.distance=distance;
    }
    @Override
    public Response execute(CollectionManager<Route> collectionMan, BufferedReader buff) throws IOException {
        buff.reset();
        Boolean cont = true;
        do {
            try {
                String c;
                String[] content = buff.readLine().split(" ");
                System.out.println(content[0]);
                if (content.length == 2) {
                    c = content[1];
                } else {
                    c = content[0];
                }
                if (c == null) throw new WrongScriptException();
                distance = Long.parseLong(c);
                cont = false;


            } catch (NumberFormatException ex) {
                System.out.println("число типа Long не найдено или вы забыли его ввести, попробуйте ввести ещё раз");
                if (another_script) throw new WrongScriptException();
            }
        }while(cont);

        return(new Response(true,this));





    }

    @Override
    public String description() {
        return("удаляю всё че равно такому расстоянию");

    }

    @Override
    public String getName() {
        return("remove_all_by_distance");
    }


}
