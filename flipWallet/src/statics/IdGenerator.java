package statics;

public class IdGenerator {
    private static Long id  = 0L;

    public static Long generateId(){
        return id++;
    }
}
