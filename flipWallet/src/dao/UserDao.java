package dao;

import model.User;
import statics.IdGenerator;

import java.util.HashMap;

public class UserDao {

    private final HashMap<String, User> registeredUser;

    public UserDao(){
        registeredUser = new HashMap<>();
    }

    public User registerUser(String userName){
        if(registeredUser.containsKey(userName)){
            System.out.println("Already has a user with name: " + userName);
            return null;
        }
        User user = new User();
        user.setUserName(userName);
        user.setUserId(IdGenerator.generateId());
        registeredUser.put(userName, user);
        return user;
    }
}
