package collec_class;



import java.io.Serializable;
import java.time.LocalDate;



public class Route implements Comparable<Route>, Serializable {
    private static final long serialVersionUID = 104L;

    private Integer id; //Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private String name; //Поле не может быть null, Строка не может быть пустой

    private Coordinates coordinates; //Поле не может быть null

    public LocalDate getCreationDate() {
        return creationDate;
    }


    private java.time.LocalDate creationDate; //, Значение этого поля должно генерироваться автоматически

    private Location from; //Поле не может быть null

    private Location to; //Поле может быть null

    private Long distance; //Поле может быть null, Значение поля должно быть больше 1


    private String userName;
    public Route(){
        this.id = this.hashCode();
        this.creationDate= LocalDate.now();
    }
    public Route(Integer id, String name, Coordinates coordinates, Location f, Location t, Long distance) {
        this.id = this.hashCode();
        this.name = name;
        this.coordinates=coordinates;
        this.from=f;
        this.to=t;
        this.distance=distance;
        this.creationDate= LocalDate.now();
    }

    public Location getTo() {
        return to;
    }

    public Location getFrom() {
        return from;
    }

    @Override
    public int compareTo(Route o) {
        int compaaaare = distance.compareTo(o.getDistance());
        return(compaaaare == 0 ? (name.compareTo(o.get_Name())): compaaaare);
    }
    public int getID(){
        return(this.id);
    }


    @Override
    public String toString() {
        return("id = "+this.id.toString() + "\n"+
                "name = "+this.name + "\n" +
                this.coordinates.toString()+ "\n"+
                "from = \n"+this.from.toString()+"\n"+
                "to = \n"+ this.to.toString() + "\n"+
                "distance = " + this.distance.toString()+ "\n" +
                "creationDate = " + this.creationDate.toString() + "\n" +
                "author = " + this.userName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Route){
            Route a = (Route) obj;
            return(a.getID()==this.id);
        }
        else return(false);
    }

    public String get_Name(){
        return(this.name);
    }
    public Long getDistance() {
        return distance;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }
    public Coordinates getCoordinates(){return(coordinates); }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setCreationDate(LocalDate lt){
        this.creationDate=lt;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }




}