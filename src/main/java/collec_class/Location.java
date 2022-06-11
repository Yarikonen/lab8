package collec_class;


import java.io.Serializable;


public class Location implements Serializable {


    private double x;
    private static final long serialVersionUID = 102L;
    @Override
    public String toString() {
        return ("     LocName = " + this.name + "\n" +
                "     Coordinates = " + this.x + " " + this.y.toString() + this.z);
    }


    private Long y; //Поле не может быть null



    private long z;




    private String name; //Строка не может быть пустой, Поле не может быть null

    public Location() {
    }

    public Location(double x, Long y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public void setZ(long z) {
        this.z = z;
    }
    public void setName(String name) {
        this.name = name;
    }

}
