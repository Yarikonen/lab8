package locales;

import java.util.ListResourceBundle;

public class GuiLanguage_en extends ListResourceBundle {
    public static final Object[][] contents = {
            {"id", "id"},
            {"routeName", "Route Name"},
            {"Coordinates", "Coordinates"},
            {"x", "X"},
            {"y", "Y"},
            {"creationDate", "Creation Date"},
            {"locationFrom", "From"},
            {"locationTo", "To"},
            {"distance", "Distance"},
            {"creator", "Creator"},


            {"username", "Username"},
            {"password", "Password"},
            {"sign up", "sign up"},
            {"log in", "Log in"},
            {"this field is required", "this field is required"},
            {"wrong password", "Incorrect password"},
            {"registration", "User has been registred'"},
            {"big login", "You're in, letsGOOOO"},


            {"help", "Help"},
            {"add", "Add "},
            {"remove", "Remove"},
            {"info", "Info"},
            {"editMenu", "Edit"},
            {"show", "show"},
            {"insert (key) {element}", "insert (key) {element}"},
            {"clear", "clear"},
            {"execute script (file path)", "execute script (file path)"},
            {"count greater semester (semester)", "count greater semester (semester)"},
            {"filter less students count (students count)", "filter less students count (students count)"},
            {"remove greater key (key)", "remove greater key (key)"},
            {"log out", "log out"},
            {"remove key (key)", "remove key (key)"},
            {"removeLover", "Remove lower {element}"},
            {"removeLast", "Remove last"},
            {"removebyDistance", "Remove by distance"},
            {"removeLast", "Remove last"},
            {"replace if greater (key) {element}", "replace if greater (key) {element}"},
            {"update (id) {element}", "update (id) {element}"},
            {"sum of students count", "sum of students count"},
            {"changeLanguage", "change language"},
            {"view", "View"},
            {"gd", "'ve done well"},
            {"bad","'ve done bad"},
            {"updateC","Update"},
            {"Table","Table"},
            {"Map","Map"},




            {"information about the collection", "information about the collection"},
            {"information about commands", "information about commands"},
            {"shows map representation of collection", "shows map representation of collection"},
            {"to add an element to the collection", "to add an element to the collection"},
            {"to delete all elements from the collection", "to delete all elements from the collection"},
            {"to execute command sequence", "to execute command sequence"},
            {"to count elements that have bigger semester", "to count elements that have bigger semester"},
            {"to filter all elements with lower students count", "to filter all elements with lower students count"},
            {"to remove all elements with greater key", "to remove all elements with greater key"},
            {"to return to login page", "to return login page"},
            {"to remove element by key", "to remove element by key"},
            {"to remove all elements that are lower than given", "to remove all elements that are lower than given"},
            {"to replace the element if the new one is greater", "to replace the element if the new one is greater"},
            {"to update the element", "to update the element"},

            {"answer:", "answer:"},
            {"wrong parameter", "wrong parameter"},
            {"parameter", "parameter"},
            {"input semester", "input semester"},
            {"send", "send"},
            {"error", "error"},
            {"collection cleaned", "collection cleaned"},
            {"no elements in collection", "no elements in collection"},
            {"element with this key already exists", "element with this key already exists"},
            {"element added to the collection", "element added to the collection"},
            {"element was not added to the collection", "element was not added to the collection"},
            {"key", "key"},
            {"input students count", "input students count"},
            {"input key", "input key"},
            {"success", "success"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
