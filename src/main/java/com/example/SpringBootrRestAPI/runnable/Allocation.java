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

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
