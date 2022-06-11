package custom.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TextField;

import java.util.function.Predicate;

public class ValidTextField extends TextField {
    private Predicate<String> validation;
    private BooleanProperty valid = new SimpleBooleanProperty();


    public void setValid(Predicate<String> validation) {
        this.validation=validation;
        textProperty().addListener((o,oldValue,newValue) -> valid.set(validation.test(newValue)) );
        valid.set(validation.test(""));
    }

    public BooleanProperty isValid() {
        return valid;
    }
}
