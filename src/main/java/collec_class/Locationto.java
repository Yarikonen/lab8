package collec_class;


import java.io.Serializable;


public class Locationto extends Location implements Serializable {

    private double x;
    private static final long serialVersionUID = 103L;

    private Long y; //Поле не может быть null

    private long z;

    private String name; //Строка не может быть пустой, Поле не может быть null
}
