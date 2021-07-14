package com.example.SpringBootrRestAPI.runnable;

import com.example.SpringBootrRestAPI.services.Services;

/**
 * This is a class contains the run method for each thread
 * that will be started
 */
public class Allocation implements Runnable {

    private final Services services;
    private final int space;
    private final String id;

    public Allocation(Services services, int space, String id) {
        this.services = services;
        this.space = space;
        this.id = id;
    }

    @Override
    public void run() {
        services.allocate(space, id);
    }
}
