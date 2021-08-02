package exalt.company.SpringBootrRestAPI.controllers;

import exalt.company.SpringBootrRestAPI.models.Server;
import exalt.company.SpringBootrRestAPI.repositories.CustomerRepository;
import exalt.company.SpringBootrRestAPI.repositories.ServerRepository;
import exalt.company.SpringBootrRestAPI.runnable.Allocation;
import exalt.company.SpringBootrRestAPI.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This Class will be as EndPoint for RestApI ( PUT , GET , .. )
 * and it will be used http://localhost:8080/serversr/*
 */
@RestController
@RequestMapping("/servers")
public class ServerController {

    @Autowired
    ServerRepository serverRepositoryDB;

    @Autowired
    CustomerRepository customerRepositoryDB;

    @Autowired
    Services services;


    /**
     * This Method will receive GET Request as
     * http://localhost:8080/servers/all to get all Servers in DataBase
     *
     * @return An response that describe status of request ok(200) with
     * Full Details Of Servers in DataBase.
     */
    @GetMapping("/all")
    public ResponseEntity getServers() {

        List<Server> servers = serverRepositoryDB.findAll();
        return ResponseEntity.ok(servers);
    }

    /**
     * This Method will receive GET Request as
     * http://localhost:8080/servers/all/sorted to get all Servers in DataBase sorted
     * depend on remain capacity in Ascending sort.
     *
     * @return An response that describe status of request ok(200) with
     * Full Details Of each Servers in DataBase in Ascending Sort.
     */
    @GetMapping("/all/sorted")
    public ResponseEntity getServersSortedBySpace() {

        List<Server> servers = serverRepositoryDB.findAll().stream()
                .sorted().collect(Collectors.toList());

        return ResponseEntity.ok(servers);
    }

    /**
     * This Method will receive GET Request as
     * http://localhost:8080/servers/range/{from}/{to} to get all Servers in DataBase in
     * the given range
     *
     * @param from start of range inclusive
     * @param to   end of range inclusive
     * @return An response that describe status of request ok(200) with
     * * Full Details Of Servers in DataBase with given range and bad request (400)
     * if the from is invalid (less than 1) or to is invalid (more than 100).
     */
    @GetMapping("/range/{from}/{to}")
    public ResponseEntity getServersInSpecificRange(@PathVariable int from, @PathVariable int to) {

        if (to > 100 || from < 1) {
            return ResponseEntity.badRequest().build();
        }

        List<Server> servers = serverRepositoryDB.findAll().stream()
                .filter(server -> server.getServerCapacity() >= from && server.getServerCapacity() <= to)
                .collect(Collectors.toList());

        return ResponseEntity.ok(servers);
    }

    /**
     * This Method will receive GET Request as
     * http://localhost:8080/servers/{id} to get A Specific Server.
     *
     * @param id An id of server we will search for
     * @return An response that describe status of request ok(200) with
     * Full Details Of Specific Server in DataBase
     * if success and not found (404) if a server id is does not exist already
     */
    @GetMapping("/{id}")
    public ResponseEntity getServer(@PathVariable String id) {

        if (!serverRepositoryDB.existsById(id)) {

            return ResponseEntity.notFound().build();
        }

        Server server = serverRepositoryDB.findById(id).get();

        return ResponseEntity.ok(server);

    }


    /**
     * This Method will receive GET Request as
     * http://localhost:8080/servers/rent to rent a given space in the server
     * from a customer is already exist in database.
     *
     * @param space A space that we need to rent in a server
     * @param id    Is An Id of Customer that want to rent a space in a server.
     * @return An response that describe status of request ok(200) if success ,
     * not found (404) if a customer id is does not exist already and bad request (400)
     * IF A space is not between 1 and 100 inclusive
     */
    @GetMapping("/rent")
    public ResponseEntity rentServer(@RequestParam("space") int space, @RequestParam("id") String id) {

        if (!checkCustomerID(id)) {
            return ResponseEntity.notFound().build();
        }

        if (!checkSpace(space)) {
            return ResponseEntity.badRequest().build();
        }

        Allocation allocation = new Allocation(services, space, id);
        Thread newCustomer = new Thread(allocation);
        newCustomer.start();

        return ResponseEntity.ok().build();
    }

    /**
     * This Method will receive GET Request as
     * http://localhost:8080/servers/listID to get a list of servers
     * that we get their ids in a request
     *
     * @param ids list of serversIDS that will get
     * @return An response that describe status of request ok(200) with
     * full details of servers that we get their ids in a request.
     */
    @GetMapping("listID")
    public ResponseEntity getServersListByIds(@RequestBody List<String> ids) {

        List<Server> servers = serverRepositoryDB.findAll().stream()
                .filter(server -> ids.contains(server.getServerID()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(servers);
    }

    /**
     * This is A helping method for rentServer to check space
     * if it is in the range from 1 to 100 inclusive.
     *
     * @param space space that we need to check
     * @return true : valid space , false : is Invalid space
     */
    private boolean checkSpace(int space) {

        return space >= 1 && space <= 100;
    }

    /**
     * This is A helping method for rentServer to check Customer ID
     * if it is in the database already or not
     *
     * @param id Customer Id that we need to check is available or not
     * @return true : if it is available , false : if it is unavailable
     */
    private boolean checkCustomerID(String id) {

        try {

            int customerID = new Integer(id);
            return customerRepositoryDB.existsById(customerID);

        } catch (Exception e) {

            return false;
        }
    }
}
