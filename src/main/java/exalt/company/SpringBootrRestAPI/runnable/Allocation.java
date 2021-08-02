package exalt.company.SpringBootrRestAPI.runnable;

import exalt.company.SpringBootrRestAPI.services.Services;
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
