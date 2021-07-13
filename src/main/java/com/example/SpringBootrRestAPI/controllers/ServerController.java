package com.example.SpringBootrRestAPI.controllers;

import com.example.SpringBootrRestAPI.models.Server;
import com.example.SpringBootrRestAPI.repo.CustomerRepository;
import com.example.SpringBootrRestAPI.repo.ServerRepository;
import com.example.SpringBootrRestAPI.runnable.Allocation;
import com.example.SpringBootrRestAPI.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/servers")
public class ServerController {

    @Autowired
    ServerRepository serverRepositoryDB;

    @Autowired
    CustomerRepository customerRepositoryDB;

    @Autowired
    Services services;


    @GetMapping("/all")
    public ResponseEntity getServers() {

        List<Server> servers = serverRepositoryDB.findAll();
        return new ResponseEntity(servers, HttpStatus.OK);
    }

    @GetMapping("/all/sorted")
    public ResponseEntity getServersSortedBySpace() {

        List<Server> servers = serverRepositoryDB.findAll();
        servers = servers.stream().sorted().collect(Collectors.toList());
        return new ResponseEntity(servers, HttpStatus.OK);
    }


    @GetMapping("/range/{from}/{to}")
    public ResponseEntity getServersInSpecificRange(@PathVariable int from , @PathVariable int to) {

        List<Server> servers = serverRepositoryDB.findAll().stream()
                .filter(server -> server.getServerCapacity() >= from && server.getServerCapacity() <= to)
                .collect(Collectors.toList());

        return new ResponseEntity(servers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getServer(@PathVariable String id) {

        try {

            Server server = serverRepositoryDB.findById(id).get();
            return new ResponseEntity(server, HttpStatus.OK);

        } catch (NoSuchElementException e) {

            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/rent")
    public ResponseEntity RentServer(@RequestParam("space") int space, @RequestParam("id") String id) {

        if (!checkCustomerID(id)) {
            return new ResponseEntity<>("Invalid customerId", HttpStatus.NOT_FOUND);
        }

        if (!checkSpace(space)) {
            return new ResponseEntity<>("Invalid space", HttpStatus.BAD_REQUEST);
        }

        Allocation allocation = new Allocation(services, space, id);
        Thread newCustomer = new Thread(allocation);
        newCustomer.start();

        return new ResponseEntity(HttpStatus.OK);
    }

    private boolean checkSpace(int space) {

        return space >= 1 && space <= 100;
    }

    private boolean checkCustomerID(String id) {

        try {

            int customerID = new Integer(id);
            return customerRepositoryDB.existsById(customerID);

        } catch (Exception e) {

            return false;
        }
    }
}
