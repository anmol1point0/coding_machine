package statics;

public class IdGenerator {
    public static int id = 0;

    public static int generateId(){
        return id++;
    }
}
