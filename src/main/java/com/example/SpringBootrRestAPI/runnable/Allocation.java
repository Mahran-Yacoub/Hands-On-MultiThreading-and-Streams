package com.example.SpringBootrRestAPI.runnable;


import com.example.SpringBootrRestAPI.services.Services;


public class Allocation implements Runnable {

    private Services services ;
    private int space ;
    private String id ;

    @Override
    public void run() {

        services.allocate(space , id);
    }


    public Allocation(Services services, int space, String id) {
        this.services = services;
        this.space = space;
        this.id = id;
    }


}
