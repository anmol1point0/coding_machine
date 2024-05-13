package com.machinecoding.restaurant.statics;

public class IdGenerator {

    private static Long id=0L;

    public static Long getId(){
        id++;
        return id;
    }
}
