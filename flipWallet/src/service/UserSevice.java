package service;

import dao.UserDao;
import model.User;

import java.util.Objects;

public class UserSevice {

    private final UserDao userDao;

    public UserSevice(UserDao userDao){
        this.userDao = userDao;
    }

    public User registerUser(String name){
        if(Objects.isNull(name)){
            System.out.println("Name cannot be empty while registering");
        }

        System.out.println("User: " + name + " registered Successfully");

        return userDao.registerUser(name);
    }
}
