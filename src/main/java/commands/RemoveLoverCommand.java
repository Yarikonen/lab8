package commands;

import collec_class.CollectionManager;
import collec_class.Route;
import server.Response;
import utils.WrongScriptException;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveLoverCommand extends Command {
    private static final long serialVersionUID = 6L;
    private final boolean another_script;
    private Integer ID;
    private final String userName;
    private Route route;
    public RemoveLoverCommand(Boolean another_script,String userName, Route route){
        this.another_script = another_script;
        this.userName=userName;
        this.route = route;


    }

    @Override
    public Response execute(CollectionManager<Route> collectionMan, BufferedReader buff) throws IOException {
        boolean cont=true;

        if (serverMode){
            if (ID==null){
                collectionMan.remove_lower(route,userName);
            }
            else{
                route= collectionMan.find_by_id(ID);
                if (route == null) {
                    return(new Response(false,this,"такого ID нет"));
                }
            }
            collectionMan.remove_lower(route,userName);
            return(new Response(true,this));
        }
        else {
            do {
                System.out.println("Хотите выбрать элемент из существуюших? da/net");
                try {
                    buff.mark(8192);
                    String decis = buff.readLine();
                    if (decis.equals("da")) {
                        System.out.println("Введите ID");
                        String c = buff.readLine();
                        ID = Integer.parseInt(c);

                        cont = false;
                    } else if (decis.equals("net")) {
                        AddCommand add = new AddCommand(another_script,userName,new Route());
                        add.addd(route, buff);
                        cont = false;
                    } else {
                        System.out.println("da/net");
                        if (another_script) throw new WrongScriptException();
                    }

                } catch (NumberFormatException exp) {
                    System.out.println("ID должен быть целым положительным");
                    if (another_script) {
                        throw new WrongScriptException();
                    }
                } catch (NullPointerException exp) {
                    exp.printStackTrace();
                    if (another_script) {
                        throw new WrongScriptException();
                    }
                }
            } while (cont);
        }

        return(new Response(true,this));

    }

    @Override
    public String description() {
        return("удалю все элементы которые меньше");

    }

    @Override
    public String getName() {
        return("remove_lover");
    }



    }

