package collec_class;


import java.io.Serializable;
import java.util.*;


public class CollectionManager<E extends Route> implements Serializable {

    private Stack<E> storage;
    private static final long serialVersionUID = 100L;
    private String collectionName= "routes";
    private final Date creationDate = new Date();
    public CollectionManager(Stack <E> storage){
        this.storage = storage;
    }
    public CollectionManager(){
        this.storage = new Stack<>();
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public Stack<E> getStorage() {
        return storage;
    }
    public void setStorage(Stack<E> storage) {
        this.storage = storage;
    }
    public void filterwithname(String name){
        for(E e:storage){
            if(e.get_Name().startsWith(name)){
                System.out.println(e);
            }
        }
    }
    public E find_by_id(int id){
        for (E e: storage) {
            if (e.getID()==id){
                return(e);
            }
        }
        return(null);
    }
    public void remove_lower(E k, String userName){
        System.out.println(k);
        storage.stream().forEach(e -> {if (e.compareTo(k)<0&&e.getUserName().equals(userName)){storage.remove(e);}});
    }
    public Stack<E> reorder(){
        Stack<E> temp2 = storage;
        Stack<E> temp = new Stack<>();
        for (int i = 0; i <= storage.size(); i++) {
            E e = storage.pop();
            temp.push(e);
        }
        storage = temp;
        return(temp);

    }

    public Long sum_distance(){
        Long sum = 0L;
        for (E e: storage) {
            sum += e.getDistance();
        }
        return(sum);
    }

    public Stack<E> findbyAuthor(String userName){
        Stack<E> authorStack = new Stack<>();
        for(E e : storage){
            if(e.getUserName().equals(userName)){
                authorStack.add(e);
            }
        }
        return(authorStack);
    }
    public void add(E e){
        storage.add(e);
    }


    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
