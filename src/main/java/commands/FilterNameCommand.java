package commands;

import collec_class.CollectionManager;
import collec_class.Route;
import server.Response;
import utils.WrongScriptException;

import java.io.BufferedReader;
import java.io.IOException;

public class FilterNameCommand extends Command {
    boolean another_script;
    String c = "";
    private static final long serialVersionUID =1001L;
    public FilterNameCommand(boolean another_script) {
        this.another_script = another_script;
    }
    @Override
    public Response execute(CollectionManager<Route> collectionMan, BufferedReader buff) throws IOException {
        buff.reset();
        Boolean cont = true;
        String cc = buff.readLine();
        if (cc == null || (cc.split(" ").length == 1 && another_script)) throw new WrongScriptException();
        String[] content = cc.split(" ");
        if (content.length == 2) {
            c = content[1].replaceAll(" ", "");
        } else if (c.isEmpty()) {
            System.out.println("Введите подстроку, вы забыли");
            c = buff.readLine();
        }
        System.out.println(c);

        return(new Response(true,this));


    }

    @Override
    public String description() {
        return("вывожу все элемент начинающиеся с данной подстроки");

    }

    @Override
    public String getName() {
        return("filter_starts_with_name");
    }
}
