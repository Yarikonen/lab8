package locales;

import java.util.ListResourceBundle;

public class GuiLanguage_ru extends ListResourceBundle {
    public static final Object[][] contents = {
            {"id", "id"},
            {"routeName", "Имя маршрута"},
            {"Coordinates", "Координаты"},
            {"x", "X"},
            {"y", "Y"},
            {"creationDate", "Дата создания"},
            {"locationFrom", "Откуда"},
            {"locationTo", "Куда"},
            {"distance", "Расстояние"},
            {"creator", "Создатель"},
            {"view", "Обзор"},


            {"username", "Имя"},
            {"password", "пааааароль"},
            {"log in", "Зайти"},
            {"wrong password", "Пароль неверный"},
            {"registration", "Вы зарегестрированы'"},
            {"big login", "Погнали"},


            {"changeLanguage", "сменить язык"},
            {"editMenu", "Изменить"},
            {"help", "Помощь"},
            {"info", "Подробнее"},
            {"removeLover", "Убивает меньшие элементы"},
            {"removeLast", "Убить последний элемент"},
            {"removebyDistance", "Удалить всё по дистанции"},
            {"removeLast", "Удалить меньше чем"},
            {"clear", "Очистить"},
            {"remove", "УДАЛИТЬ"},
            {"updateC", "Обновить"},
            {"Table","Таблица"},
            {"Map","Карта"},
            {"add", "Добавить"},


            {"gd", "сработала"},
            {"bd","сломалась"},
            };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
