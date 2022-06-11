package commands;

import collec_class.CollectionManager;
import collec_class.Route;
import server.Response;
import utils.WrongScriptException;

import java.io.BufferedReader;
import java.io.IOException;

public class UpdateByIDCommand extends Command{
    private boolean another_script;
    private int id;
    private Route podmena;
    private final String userName;
    private static final long serialVersionUID = 9L;
    public UpdateByIDCommand(Boolean another_script,String userName, Route podmena){
        this.another_script = another_script;
        this.userName = userName;
        this.podmena=podmena;

    }


    @Override
    public Response execute(CollectionManager<Route> collectionMan, BufferedReader in) throws IOException {


        if(!serverMode) {
            in.reset();
            AddCommand add = new AddCommand(another_script,userName,new Route());
            podmena= new Route();
            boolean cont = true;
            System.out.println("введите номер элемента, который хотите обновить");
            do {
                try {
                    String c;
                    String kekw = in.readLine();
                    if (kekw == null) {
                        throw new NullPointerException();
                    }
                    String[] content = kekw.split(" ");
                    if (content.length == 2) {
                        c = content[1];
                    } else {
                        c = content[0];
                    }
                    id = Integer.parseInt(c);
                    add.addd(podmena,in);
                    podmena.setId(id);
                    return(new Response(true,this));
                } catch (NumberFormatException ex) {
                    System.out.println("Возможно вы ввели не integer или забыли ввести данные, попробуйте ещё раз");
                    if (another_script) {
                        throw new WrongScriptException();
                    }
                }

            } while (cont);
        }
        return(null);



    }

    @Override
    public String description() {
        return("изменим элемент по ID");

    }

    @Override
    public String getName() {
        return("updatebyID");
    }


}
