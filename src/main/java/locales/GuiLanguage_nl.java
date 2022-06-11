package locales;

import java.util.ListResourceBundle;

public class GuiLanguage_nl extends ListResourceBundle {
    public static final Object[][] contents = {
            {"id", "id"},
            {"routeName", "Routenaam"},
            {"Coordinates", "Coördinaten"},
            {"x", "X"},
            {"y", "Y"},
            {"creationDate", "Datum van creatie"},
            {"locationFrom", "Waar"},
            {"locationTo", "Waar"},
            {"distance", "Afstand"},
            {"creator", "Schepper"},
            {"view", "Opnieuw"},


            {"username", "Naam"},
            {"password", "Wachtwoord"},
            {"log in", "Kom binnen"},
            {"wrong password", "Verkeerd wachtwoord"},
            {"registration", "Je bent geregistreerd'"},
            {"big login",
                    "Laten we gaan"},


            {"changeLanguage", "Taal wijzigen"},
            {"editMenu", "Wijziging"},
            {"help", "Helpen"},
            {"info", "Meer"},
            {"removeLover", "Doodt kleinere elementen"},
            {"removeLast", "Dood laatste element"},
            {"removebyDistance", "Alles op afstand wissen"},
            {"removeLast", "Verwijder minder dan"},
            {"clear", "Duidelijk"},
            {"remove", "VERWIJDEREN"},
            {"updateC", "Vernieuwen"},
            {"Table","Tafel"},
            {"Map","Kaart"},
            {"add", "Toevoegen"},


            {"gd", "werkte"},
            {"bd","Kapot"},
    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
