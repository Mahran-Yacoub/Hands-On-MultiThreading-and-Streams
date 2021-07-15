package com.example.SpringBootrRestAPI.runnable;

import com.example.SpringBootrRestAPI.services.Services;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * This is a class contains the run method for each thread
 * that will be started
 */
@AllArgsConstructor
@NoArgsConstructor
public class Allocation implements Runnable {

    private  Services services;
    private  int space;
    private  String id;

    @Override
    public void run() {
        services.allocate(space, id);
    }
}
